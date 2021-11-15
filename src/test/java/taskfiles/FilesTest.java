package taskfiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilesTest {

    private String ZIP_FILE = "sample-zip-file.zip";
    private String TXT_FILE = "myfile.txt";
    private String CSV_FILE = "csv.csv";
    private String XLS_URL = "https://texkom.ru/optovikam/optovye-prays-listy/";
    private String UPLOAD_URL = "http://www.csm-testcenter.org/test?do=show&subdo=common&test=file_upload";
    private String PDF_URL = "https://docs.pexip.com/admin/download_pdf.htm";
    private String TXT_URL = "https://filesamples.com/formats/txt";

    @Test
    @DisplayName("Загрузка файла по относительному пути")
    void filenameShouldDisplayedAfterUploadActionFromClasspathTest() {

        open(UPLOAD_URL);
        $("input[type='file']").uploadFromClasspath(TXT_FILE);
        $("#button").click();
        $("#item").$$("tr").get(0).$$("td").get(1).shouldHave(text(TXT_FILE));

    }

    @Test
    @DisplayName("Скачивание текстового файла и проверка его содержимого")
    void downloadSimpleTextFileTest() throws IOException {

        open(TXT_URL);
        File download = $("#output").$$(".btn-primary").get(0).download();
        String fileContent = IOUtils.toString(new FileReader(download));
        assertTrue(fileContent.contains("Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
    }

    @Test
    @DisplayName("Скачивание PDF файла")
    void pdfFileDownloadTest() throws IOException {

        open(PDF_URL);
        File pdf = $(byText("Pexip Infinity v")).download();
        PDF parsedPdf = new PDF(pdf);
        assertEquals(16, parsedPdf.numberOfPages);
        assertTrue(parsedPdf.text.contains("Release Notes"));

    }

    @Test
    @DisplayName("Скачивание XLS файла")
    void xlsFileDownloadTest() throws IOException {

        open(XLS_URL);
        File file = $("a[href*='acsess_m.xls']")
                .download();

        XLS parsedXls = new XLS(file);
        boolean checkPassed = parsedXls.excel
                .getSheetAt(0)
                .getRow(17)
                .getCell(2)
                .getStringCellValue()
                .contains("Алкотестер AT400 портативный (0,00-2,00 промилле) ЖК-дисплей с подсветкой, сменные мундштуки, звуковой сигнализатор ДЕЛЬТА");

        assertTrue(checkPassed);
    }

    @Test
    @DisplayName("Парсинг CSV файлов")
    void parseCsvFileTest() throws IOException, CsvException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(CSV_FILE);
             Reader reader = new InputStreamReader(is)) {
            CSVReader csvReader = new CSVReader(reader);

            List<String[]> strings = csvReader.readAll();
            assertEquals(4, strings.size());

        }
    }

    @Test
    @DisplayName("Парсинг ZIP файлов")
    void parseZipFileTest() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(ZIP_FILE);
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        }
    }

}
