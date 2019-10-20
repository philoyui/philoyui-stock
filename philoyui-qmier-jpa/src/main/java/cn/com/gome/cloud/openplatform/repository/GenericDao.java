package cn.com.gome.cloud.openplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by yangyu-ds on 2016/9/21.
 */
@NoRepositoryBean
public interface GenericDao<T, D extends Serializable> extends JpaRepository<T,D>,JpaSpecificationExecutor<T> {


}
