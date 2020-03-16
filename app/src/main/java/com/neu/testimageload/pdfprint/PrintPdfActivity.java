package com.neu.testimageload.pdfprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.neu.testimageload.R;
import com.neu.testimageload.listitem.other.SidebarUtils;

import java.io.FileOutputStream;
import java.net.URL;

public class PrintPdfActivity extends AppCompatActivity {

    private static String TAG = "PrintPdfActivity";
    private EditText pdf_edittext;
    private Button create_pdf;
    private Button button_riqi;
    private TextView text_riqi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_pdf);

        pdf_edittext = findViewById(R.id.edittext);
        create_pdf = findViewById(R.id.pdf_button);
        button_riqi = findViewById(R.id.button_select);
        text_riqi = findViewById(R.id.text_tiqi);

        create_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pdfName = System.currentTimeMillis() + "PDF" + ".pdf";
                String srtImgPath = Environment.getExternalStorageDirectory() + "/DCIM/PDF/"+pdfName;
                textTransformPdf(pdf_edittext.getText().toString(),srtImgPath);
            }
        });

        button_riqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SidebarUtils.initSelectStartTime(PrintPdfActivity.this,text_riqi);
            }
        });
    }

    public void textTransformPdf(String content, String pdf_save_address) {
        Document doc = new Document();// 创建一个document对象
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(pdf_save_address); // pdf_address为Pdf文件保存到sd卡的路径
            PdfWriter.getInstance(doc, fos);
            doc.open();
            doc.setPageCount(1);
            doc.add(new Paragraph(content, setChineseFont()));// result为保存的字符串            // ,setChineseFont()为pdf字体
            doc.add(new Paragraph(content, setChineseFont()));// result为保存的字符串            // ,setChineseFont()为pdf字体
            doc.add(new Paragraph(content, setChineseFont()));// result为保存的字符串            // ,setChineseFont()为pdf字体
            // 一定要记得关闭document对象

            doc.close();
            Intent intent = new Intent(PrintPdfActivity.this,PdfReadActivity.class);
            intent.putExtra("path",pdf_save_address);
            startActivity(intent);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public Font setChineseFont() {
        BaseFont bf = null;
        Font fontChinese = null;
        try {            // STSong-Light : Adobe的字体            // UniGB-UCS2-H : pdf 字体
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            fontChinese = new Font(bf, 12, Font.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fontChinese;
    }

    public void imgTransformPdf(String[] imgPaths, String pdf_save_address) {
        Document doc = new Document(PageSize.A4, 0, 0, 0, 0);
        try {            //获取PDF书写器
            PdfWriter.getInstance(doc, new FileOutputStream(pdf_save_address));            //打开文档
            doc.open();            //图片对象
            Image img = null;            //遍历
            for (int i = 0; i < imgPaths.length; i++) {
                //获取图片
                img = Image.getInstance(new URL(imgPaths[i]));                //使图片与A4纸张大小自适应
                img.scaleToFit(new Rectangle(PageSize.A4));                //添加到PDF文档
                doc.add(img);                //下一页，每张图片一页
                doc.newPage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {            //关闭文档
            doc.close();
        }
    }
}
