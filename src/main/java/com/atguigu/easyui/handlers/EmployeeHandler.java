package com.atguigu.easyui.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.easyui.entities.Employee;
import com.atguigu.easyui.services.EmployeeService;
import com.github.pagehelper.Page;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/remoteDataProvider")
	public void remoteDataProvider(HttpServletResponse response) throws Exception{
		Thread.sleep(3000);
		response.getWriter().write("恭喜你取到了我的数据！");
	}
	
	@RequestMapping("/saveEmployee")
	public void saveEmployee(HttpServletResponse response,Employee employee) throws Exception{
		employeeService.saveEmployee(employee);
		response.getWriter().write("success");
	}
	
	@RequestMapping("/updateEmployee")
	public void updateEmp(Employee employee, HttpServletResponse response) throws Exception {
		employeeService.updateEmployee(employee);
		
		System.err.println(employee+"!!!!!!!!!!");
		response.getWriter().write("success");
	}
	
	@RequestMapping("/removeEmp/{empId}")
	public void removeEmp(@PathVariable("empId") Integer empId, HttpServletResponse response) throws Exception {
		
		employeeService.removeEmployee(empId);
		
		response.getWriter().write("success");
	}
	
	@ResponseBody
	@RequestMapping("/getEmpPageRemote")
	public Map<String, Object> getEmpPageRemote(@RequestParam("page") int pageNum, @RequestParam("rows") int pageSize) {
		
		List<Employee> list = employeeService.getEmpPageList(pageNum, pageSize);
		
		Page<Employee> page = (Page<Employee>) list;
		//获取总记录数
		long total = page.getTotal();
		
		//创建Map对象用来存放响应数据
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		
		return jsonMap;
	}
	
}
