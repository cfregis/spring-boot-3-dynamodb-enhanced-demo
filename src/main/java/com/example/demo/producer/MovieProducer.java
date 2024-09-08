package com.example.demo.producer;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MovieProducer {

    @Autowired
    private SqsTemplate sqsTemplate;

    @Value("${aws.queueName}")
    private String queueName;

    @SqsListener( value = "${aws.queueName}" )
    public void send(Movie movie) {

        log.info("Message sent on listen method at {}", movie.toString());
        try {
            String payload = new ObjectMapper().writeValueAsString(movie);
            sqsTemplate
                    .send(sqsSendOptions ->
                            sqsSendOptions
                                    .queue(queueName)
                                    .payload(payload)
                    );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
