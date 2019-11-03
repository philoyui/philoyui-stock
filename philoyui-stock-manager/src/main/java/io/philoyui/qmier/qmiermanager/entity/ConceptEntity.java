package io.philoyui.qmier.qmiermanager.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 概念
 */
@Entity
public class ConceptEntity implements Serializable {

    @Id
    private Long id;

    /**
     * 概念名称
     */
    private String name;

    /**
     * 最后更新时间
     */
    private Date modifyTime;



}
