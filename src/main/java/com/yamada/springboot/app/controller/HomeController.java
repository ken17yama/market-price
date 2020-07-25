package com.yamada.springboot.app.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Controller
public class HomeController {
	
	@Autowired
    protected ResourceLoader resourceLoader;
		
	@GetMapping("/home")
	public String getHome() throws IOException, TesseractException{
		
		String pathName = "img/";
		String fileName = "sample_1.jpg";
        Resource resource = resourceLoader.getResource("classpath:" + pathName + fileName);
		
		// 画像を読み込む
        File file = resource.getFile();
        
        BufferedImage img = ImageIO.read(file);
        
        int resizeW = img.getWidth() * 5;
        int resizeH = img.getHeight() * 5;
        // 画像サイズ変更
        BufferedImage scaleImg = new BufferedImage(resizeW, resizeH, BufferedImage.TYPE_3BYTE_BGR);
        scaleImg.createGraphics().drawImage(
          img.getScaledInstance(resizeW, resizeH, Image.SCALE_AREA_AVERAGING),
          0, 0, resizeW, resizeH, null);

        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\ken17\\Documents\\workspace-spring-tool-suite-4-4.6.0.RELEASE\\market-price\\src\\main\\resources\\lang"); // 言語ファイル（jpn.traineddata））の場所を指定
        tesseract.setLanguage("jpn"); // 解析言語は「日本語」を指定

        // 解析
        String str = tesseract.doOCR(scaleImg);

        // 結果
        System.out.println(str);
		
		return "hello";
	}

}
