import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/10/16 - 13:15
 **/
public class CreatePDFMainTest {

    public static void main(String[] args) throws Exception {
        Document document = new Document(PageSize.A4);
        //第二步，创建Writer实例
        PdfWriter.getInstance(document, new FileOutputStream("D:\\hello.pdf"));
        //创建中文字体
        BaseFont bfchinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfchinese, 12, Font.NORMAL);
        //第三步，打开文档
        document.open();
        //第四步，写入内容
        Paragraph paragraph = new Paragraph("hello world", fontChinese);
        document.add(paragraph);
        //第五步，关闭文档
        document.close();
    }
}
