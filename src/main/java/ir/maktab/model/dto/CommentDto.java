package ir.maktab.model.dto;

import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Expert;

/**
 * @author Mahsa Alikhani m-58
 */
public class CommentDto {
    private long id;
    private Double rate;
    private String comment;
    private Client client;
    private Expert expert;
}
