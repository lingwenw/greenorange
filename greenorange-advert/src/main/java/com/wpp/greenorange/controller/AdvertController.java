package com.wpp.greenorange.controller;

import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.Advert;
import com.wpp.greenorange.service.AdvertService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * (Advert)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:36:49
 */
@RestController
@RequestMapping("/advert")
public class AdvertController {
    /**
     * 服务对象
     */
    @Resource
    private AdvertService advertService;

    @RequestMapping("/uploadImg")
    @PreAuthorize("hasAnyAuthority('advert_write','super_admin')")
    public ModelAndView uploadImg(String option, String herf,MultipartFile upload, HttpServletRequest request) throws IOException {
        ModelAndView mv=new ModelAndView("redirect:/admin/advertising.html");
        //定义文件保存的本地路径
        String localPath = "E:/greenorange/img/";
        File file = new File(localPath);
        //如果目录不存在，创建该目录
        if (!file.exists()) {
            file.mkdirs();
        }
        //从MultipartFile对象中文件真实名称
        String Filename = upload.getOriginalFilename();
        //生成uuid作为文件名称
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //获得文件类型（可以判断如果不是图片，禁止上传）
        //得到 文件名
        Filename = uuid + "_" + Filename;
        upload.transferTo(new File(localPath + Filename));
        String img = "/img/" + Filename;
        Boolean insert = null;
        if (img != null&& option!=null) {
            //文件保存路径
            //把图片的相对路径保存至数据库
            Advert advert = new Advert();

            if (option.equals("轮播图")){
                int position_id=1;
                advert.setPositionId(position_id);
                advert.setHref(herf);
                advert.setImgUrl(img);
                advert.setDescribe(option);
                //当前时间
                Date date = new Date(System.currentTimeMillis());
                advert.setCreateTime(date);
                insert= advertService.insert(advert);
            }else {
                int position_id=2;
                advert.setPositionId(position_id);
                advert.setHref(herf);
                advert.setImgUrl(img);
                advert.setDescribe(option);
                //当前时间
                Date date = new Date(System.currentTimeMillis());
                advert.setCreateTime(date);
                insert= advertService.insert(advert);
            }

        }
        System.out.println(insert ? "上传成功":"上传失败");
        return mv;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/getOne")
    public Advert getOne(Integer id) {
        return this.advertService.findById(id);
    }

    /**
     * 根据PositionId查询
     * @param PositionId
     * @return
     */
    @RequestMapping("/getAllByPositionId")
    public List getAllByPid(Integer PositionId) {
        Advert advert=new Advert();
        advert.setPositionId(PositionId);
        List<Advert> allByCondition = advertService.findAllByCondition(advert);
        return allByCondition;
    }
    /**
     *
     * @return查询全部数据返回页面
     */
    @RequestMapping("/getAllAdvert")
    public PageInfo<Advert> getAllAdvert(Integer pageNum,Integer pageSize) {
        return advertService.findAllLimit(pageNum,pageSize);
    }

    @RequestMapping("/removeAdvert")
    @PreAuthorize("hasAnyAuthority('advert_write','super_admin')")
    public boolean removeAdvert(Integer ids) {
        Boolean flag=false;
        if (ids!=null){
            flag = advertService.deleteById(ids);
        }
        System.out.println(flag);
        return flag;
    }

    @RequestMapping("/setAdvert")
    @PreAuthorize("hasAnyAuthority('advert_write','super_admin')")
    public ModelAndView setAdvert(Integer id,String positions,String herf,MultipartFile upload) throws IOException {
        ModelAndView mv=new ModelAndView("redirect:/admin/advertising.html");
        //定义文件保存的本地路径
        String localPath = "E:/greenorange/img/";
        File file = new File(localPath);
        //如果目录不存在，创建该目录
        if (!file.exists()) {
            file.mkdirs();
        }
        //从MultipartFile对象中文件真实名称
        String Filename = upload.getOriginalFilename();
        //生成uuid作为文件名称
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //获得文件类型（可以判断如果不是图片，禁止上传）
        //得到 文件名
        Filename = uuid + "_" + Filename;
        upload.transferTo(new File(localPath + Filename));
        String img = "/img/" + Filename;
        if (img != null&& positions!=null&&id!=null&&herf!=null) {
            //文件保存路径
            //把图片的相对路径保存至数据库
            Advert advert = new Advert();
            //当前时间
            Date date = new Date(System.currentTimeMillis());

            Boolean update;
            if (positions.equals("轮播图")){
                int position_id=1;
                advert.setPositionId(position_id);
                advert.setHref(herf);
                advert.setImgUrl(img);
                advert.setDescribe(positions);
                advert.setUpdateTime(date);
                advert.setId(id);
                update= advertService.update(advert);
            }else {
                int position_id=2;
                advert.setPositionId(position_id);
                advert.setHref(herf);
                advert.setImgUrl(img);
                advert.setDescribe(positions);
                advert.setUpdateTime(date);
                advert.setId(id);
                update= advertService.update(advert);
            }
        }
        return mv;
    }

}