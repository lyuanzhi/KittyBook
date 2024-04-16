package com.jumbox.demo.controller.KittyController;

import com.jumbox.demo.common.utils.CommonResult;
import com.jumbox.demo.service.KittyService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import net.coobird.thumbnailator.Thumbnails;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/kitty")
@Api(value = "小猫接口", tags = {"kitty controller"})
public class Kitty {

    @Resource
    KittyService kittyService;

    private static final Set<String> SUPPORTED_IMG_FORMATS =
            new HashSet<>(Arrays.asList("bmp", "jpg", "jpeg", "png", "gif"));

    @PostMapping(value = "/addKitty")
    public CommonResult addKitty(@RequestParam("name") String name,@RequestParam("kittySex") String kittySex,
                                 @RequestParam("kittyHealth") String kittyHealth,@RequestParam("hairColor") String hairColor,
                                 @RequestParam("kittySterilization") String kittySterilization,@RequestParam("kittySterilizationTime") String kittySterilizationTime,
                                 @RequestParam("kittyBirthday") String kittyBirthday,@RequestParam("firstSeen") String firstSeen,
                                 @RequestParam("kittyCharacter") String kittyCharacter,@RequestParam("kittyAppearance") String kittyAppearance) {

        return CommonResult.success(kittyService.addKitty(
                name,kittySex,kittyHealth,hairColor,kittySterilization,kittySterilizationTime,kittyBirthday,firstSeen,kittyCharacter,kittyAppearance));
    }

    @PostMapping(value = "/selectKittyTopFive")
    public CommonResult selectKittyTopFive() {

        return CommonResult.success(kittyService.selectKittyTopFive());
    }

    @PostMapping(value = "/selectALLByPage")
    public CommonResult selectALLByPage(@RequestParam("page") Integer page) {

        return CommonResult.success(kittyService.selectALLByPage(page));
    }

    @PostMapping(value = "/selectKittyInfo")
    public CommonResult selectKittyInfo(@RequestParam("id") String id) {

        return CommonResult.success(kittyService.selectKittyInfo(id));
    }

    @PostMapping(value = "/selectKittyByName")
    public CommonResult selectKittyByName(@RequestParam("name") String name) {

        return CommonResult.success(kittyService.selectKittyByName(name));
    }

    @PostMapping(value = "/addHeart")
    public CommonResult addHeart(@RequestParam("id") String id, @RequestParam("heartNum") Integer heartNum) {

        return CommonResult.success(kittyService.addHeart(id, heartNum));
    }

    @PostMapping(value = "/getComment")
    public CommonResult getComment(@RequestParam("kittyId") String kittyId) {

        return CommonResult.success(kittyService.getComment(kittyId));
    }

    @PostMapping(value = "/addComment")
    public CommonResult addComment(@RequestParam("kittyId") String kittyId,@RequestParam("parentId") String parentId,
                                   @RequestParam("name") String name,@RequestParam("responseName") String responseName,
                                   @RequestParam("content") String content) {

        return CommonResult.success(kittyService.addComment(kittyId,parentId,name,responseName,content));
    }

    @PostMapping(value = "/addLike")
    public CommonResult addLike(@RequestParam("id") String id,@RequestParam("likeNum") Integer likeNum) {

        return CommonResult.success(kittyService.addLike(id, likeNum));
    }

    @PostMapping("/uploadAvatarImg/{id}")
    public CommonResult uploadAvatarImg(@RequestBody MultipartFile avatarImg, @PathVariable String id) {
        if (!kittyService.isExist(id)) {
            return CommonResult.failed("id不存在！");
        }
        if (avatarImg == null) {
            return CommonResult.failed("上传失败，图片不存在!");
        }
        String fileName = Objects.requireNonNull(avatarImg.getOriginalFilename()).toLowerCase();
        String imgExtension;
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            imgExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            imgExtension = "";
        }
        if (!SUPPORTED_IMG_FORMATS.contains(imgExtension)) {
            return CommonResult.failed("上传失败，请选择BMP、JPG、JPEG、PNG、GIF文件！");
        }
        try {
            byte[] bytes = avatarImg.getBytes();
            File dir = new File("./avatarImages");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, String.format("avatar_%s.%s", id, "jpg"));
            OutputStream out = new FileOutputStream(file);
            out.write(bytes);
            out.close();
            //图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
            Thumbnails.of(file).scale(1f).outputQuality(0.25f).toFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return CommonResult.failed("服务器接收文件失败！");
        }
        String avatarUrl = String.format("https://lyuanzhi.com:8084/avatar_%s.%s", id, "jpg");
        return CommonResult.success(kittyService.uploadAvatarImg(id, avatarUrl));
    }

    @PostMapping("/uploadKittyImg/{id}")
    public CommonResult uploadKittyImg(@RequestBody MultipartFile kittyImg, @PathVariable String id) {
        if (!kittyService.isExist(id)) {
            return CommonResult.failed("id不存在！");
        }
        if (kittyImg == null) {
            return CommonResult.failed("上传失败，图片不存在!");
        }
        String fileName = Objects.requireNonNull(kittyImg.getOriginalFilename()).toLowerCase();
        String imgExtension;
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            imgExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            imgExtension = "";
        }
        if (!SUPPORTED_IMG_FORMATS.contains(imgExtension)) {
            return CommonResult.failed("上传失败，请选择BMP、JPG、JPEG、PNG、GIF文件！");
        }
        try {
            byte[] bytes = kittyImg.getBytes();
            File dir = new File("./kittyImages");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, String.format("kitty_%s.%s", id, "jpg"));
            OutputStream out = new FileOutputStream(file);
            out.write(bytes);
            out.close();
            //图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
            Thumbnails.of(file).scale(1f).outputQuality(0.25f).toFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return CommonResult.failed("服务器接收文件失败！");
        }
        String imgUrl = String.format("https://lyuanzhi.com:8084/kitty_%s.%s", id, "jpg");
        return CommonResult.success(kittyService.uploadKittyImg(id, imgUrl));
    }

    @PostMapping("/addFund")
    public CommonResult addFund(@RequestParam("title") String title,@RequestParam("content") String content,
                                @RequestParam("payment") Integer payment) {

        return CommonResult.success(kittyService.addFund(title, content, payment));
    }

    @PostMapping("/selectMonthlyFund")
    public CommonResult selectMonthlyFund() {

        return CommonResult.success(kittyService.selectMonthlyFund());
    }
}
