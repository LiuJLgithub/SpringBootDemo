package com.example.demo.ch3.controller;
/**
 * 演示文件上传，使用MulipartFile来支持文件上传
 * @author Liu
 *
 */

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class MultipartFileDemoController {
	@PostMapping("/fileUpload.do")
	@ResponseBody
//	public String handleFormUpload(@RequestParam("file") MultipartFile file) throws IOException{//可以使用
		public String handleFormFileUpload(String name,MultipartFile file) throws IOException{//可以使用
		if(!file.isEmpty()&&file!=null){
			String fileName=file.getOriginalFilename();
			System.out.println("fileName of uploading:"+fileName);
			InputStream inputStream=file.getInputStream();
			//处理上传内容
			return "success";
		}
		return "failure";
	}
	@PostMapping("/filesUpload.do")
	@ResponseBody
//	public String handleFormUpload(@RequestParam("file") MultipartFile file) throws IOException{//可以使用
	public String handleFormFilesUpload(String name,MultipartFile[] file) throws IOException{//可以使用
		String msg="failure";
		System.out.println("多文件个数为："+file.length);
		for (MultipartFile fil : file) {
			if(!fil.isEmpty()&&fil!=null){
				//1、获取文件的原始文件名称
				String fileName=fil.getOriginalFilename();
				System.out.println("filesName of uploading:"+fileName);
				//2、打印文件的内容
				InputStream ins=fil.getInputStream();
				byte[] b=new byte[2];
				System.out.println("文件的内容为：");
				while(ins.read(b)!=-1){
					System.out.println(new String(b));
				}
				//处理上传内容
				msg= "success";
			}
		}
		return msg;
	}
}
