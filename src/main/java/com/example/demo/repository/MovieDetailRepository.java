package com.example.demo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.example.demo.model.MovieDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
@Repository
@AllArgsConstructor
public class MovieDetailRepository {

    final private DynamoDBMapper dynamoDBMapper;

    public MovieDetail createMovieDetail(MovieDetail movie){
        dynamoDBMapper.save(movie);
        return movie;
    }

    public MovieDetail getMovieDetailById(String id){
        return dynamoDBMapper.load(MovieDetail.class, id);
    }

    public PaginatedScanList<MovieDetail> getMovieDetailList(){
        DynamoDBScanExpression filter =  new DynamoDBScanExpression();
        return dynamoDBMapper.scan(MovieDetail.class, filter);
    }

    public MovieDetail updateMovieDetail(String id, MovieDetail movie){
        MovieDetail load = dynamoDBMapper.load(MovieDetail.class, id);
        // map these two entity
        dynamoDBMapper.save(load);

        return dynamoDBMapper.load(MovieDetail.class, id);
    }

    public String deleteMovieDetail(String id){
        MovieDetail load = dynamoDBMapper.load(MovieDetail.class, id);
        if(load != null){
            dynamoDBMapper.delete(load);
            return load.getId() + " get deleted !";
        } else {
            return null;
        }

    }
}