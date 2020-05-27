package com.how2java.tmall.controller;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page4Navigator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Api(value = "分类",tags = "分类管理")
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    /*1. 首选通过CategoryService 保存到数据库
    2. 然后接受上传图片，并保存到 img/category目录下
    3. 文件名使用新增分类的id
    4. 如果目录不存在，需要创建
    5. image.transferTo 进行文件复制
    6. 调用ImageUtil的change2jpg 进行文件类型强制转换为 jpg格式
    7. 保存图片*/
    @ApiOperation(value="添加",notes = "新增分类")
    @PostMapping("/categories")
    public Object add(Category bean, MultipartFile image, HttpServletRequest request) throws Exception {
        categoryService.add(bean);
        System.out.println("新增分类id"+bean.getId());
        saveOrUpdateImageFile(bean, image, request);
        return bean;
    }

    public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request)
            throws IOException {
        File imageFolder= new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,bean.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }

    /*
    1. 首先根据id 删除数据库里的数据
    2. 删除对应的文件
    3. 返回 null, 会被RESTController 转换为空字符串。*/
    @ApiOperation(value="删除",notes = "删除分类")
    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id") int id,HttpServletRequest request) throws Exception{
        categoryService.delete(id);
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return null;
    }

    @ApiOperation(value="查询",notes = "查询分类")
    @GetMapping("/categories/{id}")
    public Category get(@PathVariable("id") int id) throws Exception {
        Category bean=categoryService.get(id);
        return bean;
    }

    @ApiOperation(value="更新",notes = "更新分类")
    @PutMapping("/categories/{id}")
    public Object update(Category bean, MultipartFile image,HttpServletRequest request) throws Exception {
        String name = request.getParameter("name");
        bean.setName(name);
        categoryService.update(bean);

        if(image!=null) {
            saveOrUpdateImageFile(bean, image, request);
        }
        return bean;
    }

    @ApiOperation(value="查询所有",notes = "分页查询分类")
    @GetMapping("/categories")
    public Page4Navigator<Category> list(@ApiParam(value="start")@RequestParam(value = "start", defaultValue = "0") int start, @ApiParam(value="size")@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;   //start默认为0,但输入值小于0会引发判断
        Page4Navigator<Category> page =categoryService.list(start, size, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
        return page;
    }


}
