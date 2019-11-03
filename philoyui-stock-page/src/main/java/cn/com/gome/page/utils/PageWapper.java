package cn.com.gome.page.utils;

import cn.com.gome.cloud.openplatform.common.PageObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanzenggui on 2016/10/10.
 */
public class PageWapper {
    public static final int MAX_PAGE_ITEM_DISPLAY = 5;
    private int totalPages;
    private long totalElements;
    private int pagenumber=0;
    private int pagesize=10;
    private int numberOfElements;
    private boolean first;
    private boolean last;
    private List content = new ArrayList();
    private boolean hasNext;
    private boolean hasPrevious;
    private String url;
    private List<PageItem> items;
    private int currentNumber;

    public PageWapper(){
        // Do nothing because of X and Y.
    }

    public PageWapper(PageObject pageObject, String url){
        this.content=pageObject.getContent();
        this.first=pageObject.isFirst();
        this.totalPages=pageObject.getTotalPages();
        this.totalElements=pageObject.getTotalElements();
        this.pagenumber=pageObject.getNumber();
        this.pagesize=pageObject.getSize();
        this.numberOfElements=pageObject.getNumberOfElements();
        this.last=pageObject.isLast();
        this.hasNext=pageObject.hasNext();
        this.hasPrevious=pageObject.hasPrevious();
        this.url=url;

        items = new ArrayList<>();

        currentNumber = pageObject.getNumber() + 1; //start from 1 to match page.page

        int start;

        int size;

        if (pageObject.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY){
            start = 1;
            size = pageObject.getTotalPages();
        } else {
            if (currentNumber <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY/2){
                start = 1;
                size = MAX_PAGE_ITEM_DISPLAY;
            } else if (currentNumber >= pageObject.getTotalPages() - MAX_PAGE_ITEM_DISPLAY/2){
                start = pageObject.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1;
                size = MAX_PAGE_ITEM_DISPLAY;
            } else {
                start = currentNumber - MAX_PAGE_ITEM_DISPLAY/2;
                size = MAX_PAGE_ITEM_DISPLAY;
            }
        }

        for (int i = 0; i<size; i++){
            items.add(new PageItem(start+i, (start+i)==currentNumber));
        }
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public List<PageItem> getItems() {
        return items;
    }

    public void setItems(List<PageItem> items) {
        this.items = items;
    }

    public int getPagenumber() {
        return pagenumber;
    }

    public void setContent(List content) {
        this.content = content;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List getContent() {
        return content;
    }

    public void setPagenumber(int pagenumber) {
        this.pagenumber = pagenumber;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isLast() {
        return last;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }


    public class PageItem {
        private int number;
        private boolean current;
        public PageItem(int number, boolean current){
            this.number = number;
            this.current = current;
        }

        public int getNumber(){
            return this.number;
        }

        public boolean isCurrent(){
            return this.current;
        }
    }
}
