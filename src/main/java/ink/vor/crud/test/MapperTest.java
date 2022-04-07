package ink.vor.crud.test;

import ink.vor.crud.dao.DepartmentMapper;
import ink.vor.crud.dao.EmployeeMapper;
import ink.vor.crud.pojo.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试DAO层工作
 * 推荐使用Spring的单元测试，自动注入需要的组件
 * 导入SpringTest模块
 * @author muquanrui
 * @date 16/02/2022 13:49
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;

    // 测试DepartmentMapper
    @Test
    public void testCRUD() {
        // // 1. 创建SpringIOC容器
        // ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // // 2. 从容器获取mapper
        // DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);

        // 1. 插入几个部门
        // departmentMapper.insertSelective(new Department(null, "开发部"));
        // departmentMapper.insertSelective(new Department(null, "测试部"));

        // // 2. 生成一些员工数据，测试员工插入
        // employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "Jerry@qq.com", 1));

        // 3. 批量插入多个员工，使用可以执行批量操作的sqlSession
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null, uid, "M", uid + "@qq.com", 1));
        }
        System.out.println("Batch finished.");
    }
}
