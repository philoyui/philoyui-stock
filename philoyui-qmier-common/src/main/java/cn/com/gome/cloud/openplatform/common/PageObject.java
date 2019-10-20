package cn.com.gome.cloud.openplatform.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyu-ds on 2016/9/23.
 */
public class PageObject<T extends Serializable> implements Serializable {

    private int totalPages;

    private long totalElements;

    private int number;

    private int size;

    private int numberOfElements;

    private boolean first;

    private boolean last;

    private List<T> content = new ArrayList<>();

    private boolean hasNext;

    private boolean hasPrevious;

    public PageObject() {
        //
    }

    public PageObject(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public int getNumber() {
        return this.number;
    }

    public int getSize() {
        return this.size;
    }

    public int getNumberOfElements() {
        return this.numberOfElements;
    }

    public List<T> getContent() {
        return this.content;
    }

    public boolean hasContent() {
        return !content.isEmpty();
    }

    public boolean isFirst() {
        return this.first;
    }

    public boolean isLast() {
        return this.last;
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public boolean hasPrevious() {
        return this.hasPrevious;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}
