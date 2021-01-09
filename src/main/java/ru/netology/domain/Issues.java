package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode


public class Issues {
    private int id;
    private String author;
    private String header;
    private String body;
    private Boolean openClosed;
    private HashSet<String> lable;
    private HashSet<String> assignee;


}
