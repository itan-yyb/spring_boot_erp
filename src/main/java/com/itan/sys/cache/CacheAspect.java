package com.itan.sys.cache;

import com.itan.sys.entity.Dept;
import com.itan.sys.entity.User;
import com.itan.sys.vo.DeptVo;
import com.itan.sys.vo.UserVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@EnableAspectJAutoProxy
public class CacheAspect {
    /**
     * 日志出处
     */
    private Log log= LogFactory.getLog(CacheAspect.class);
    //声明一个缓存容器
    private Map<String,Object> CACHE_CONTAINER=new HashMap<>();

    //声明切面表达式
    private static final String POINTCUT_DEPT_ADD="execution(* com.itan.sys.service.impl.DeptServiceImpl.save(..))";
    private static final String POINTCUT_DEPT_UPDATE="execution(* com.itan.sys.service.impl.DeptServiceImpl.updateById(..))";
    private static final String POINTCUT_DEPT_GET="execution(* com.itan.sys.service.impl.DeptServiceImpl.getById(..))";
    private static final String POINTCUT_DEPT_DELETE="execution(* com.itan.sys.service.impl.DeptServiceImpl.removeById(..))";

    private static final String CACHE_DEPT_PROFIX="dept:";

    /**
     * 添加切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_DEPT_ADD)
    public Object cacheDeptAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Dept obj = (Dept) joinPoint.getArgs()[0];
        Boolean res=(Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX+obj.getId(),obj);
        }
        return res;
    }
    /**
     * 修改切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_DEPT_UPDATE)
    public Object cacheDeptUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        DeptVo deptVo = (DeptVo) joinPoint.getArgs()[0];
        Boolean issuccess=(Boolean) joinPoint.proceed();
        if (issuccess){
            Dept dept=(Dept) CACHE_CONTAINER.get(CACHE_DEPT_PROFIX+deptVo.getId());
            if(dept==null){
                dept=new Dept();
            }
            BeanUtils.copyProperties(deptVo,dept);
            log.info("部门对象缓存已更新"+CACHE_DEPT_PROFIX+deptVo.getId());
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX+dept.getId(),dept);
        }
        return issuccess;
    }
    /**
     * 删除切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_DEPT_DELETE)
    public Object cacheDeptDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean issuccess=(Boolean) joinPoint.proceed();
        if (issuccess){
           //清除缓存
            CACHE_CONTAINER.remove(CACHE_DEPT_PROFIX+id);
            log.info("部门对象缓存已删除"+CACHE_DEPT_PROFIX+id);
        }
        return issuccess;
    }
    /**
     * 查询切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_DEPT_GET)
    public Object cacheDeptGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer arg = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res = CACHE_CONTAINER.get(CACHE_DEPT_PROFIX + arg);
        if(res!=null){
            log.info("已从缓存里面找到部门对象"+CACHE_DEPT_PROFIX + arg);
            return res;
        }else{
            log.info("未从缓存里面找到部门对象，去数据库查询并放入缓存");
            Dept dept=(Dept) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX+dept.getId(),dept);
            return dept;
        }
    }
    /********************************用户切入***********************************/
    private static final String POINTCUT_USER_ADD="execution(* com.itan.sys.service.impl.UserServiceImpl.save(..))";
    private static final String POINTCUT_USER_UPDATE="execution(* com.itan.sys.service.impl.UserServiceImpl.updateById(..))";
    private static final String POINTCUT_USER_GET="execution(* com.itan.sys.service.impl.UserServiceImpl.getById(..))";
    private static final String POINTCUT_USER_DELETE="execution(* com.itan.sys.service.impl.UserServiceImpl.removeById(..))";

    private static final String CACHE_USER_PROFIX="user:";
    /**
     * 添加切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_USER_ADD)
    public Object cacheUserAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        User obj = (User) joinPoint.getArgs()[0];
        Boolean res=(Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_USER_PROFIX+obj.getId(),obj);
        }
        return res;
    }
    /**
     * 修改切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_USER_UPDATE)
    public Object cacheUserUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        User userVo = (User) joinPoint.getArgs()[0];
        Boolean issuccess=(Boolean) joinPoint.proceed();
        if (issuccess){
            User user=(User) CACHE_CONTAINER.get(CACHE_USER_PROFIX+userVo.getId());
            if(user==null){
                user=new User();
            }
            BeanUtils.copyProperties(userVo,user);
            log.info("用户对象缓存已更新"+CACHE_USER_PROFIX+userVo.getId());
            CACHE_CONTAINER.put(CACHE_USER_PROFIX+user.getId(),user);
        }
        return issuccess;
    }
    /**
     * 删除切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_USER_DELETE)
    public Object cacheUserDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean issuccess=(Boolean) joinPoint.proceed();
        if (issuccess){
            //清除缓存
            CACHE_CONTAINER.remove(CACHE_USER_PROFIX+id);
            log.info("用户对象缓存已删除"+CACHE_USER_PROFIX+id);
        }
        return issuccess;
    }
    /**
     * 查询切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_USER_GET)
    public Object cacheUserGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer arg = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res = CACHE_CONTAINER.get(CACHE_USER_PROFIX + arg);
        if(res!=null){
            log.info("已从缓存里面找到用户对象"+CACHE_USER_PROFIX + arg);
            return res;
        }else{
            log.info("未从缓存里面找到用户对象，去数据库查询并放入缓存");
            User user=(User) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_USER_PROFIX+user.getId(),user);
            return user;
        }
    }
}