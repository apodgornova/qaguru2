package taskfiles;

import com.codeborne.pdftest.PDF;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilesTest {

    @Test
    @DisplayName("Загрузка файла по относительному пути")
    void filenameShouldDisplayedAfterUploadActionFromClasspathTest() {

        open("http://www.csm-testcenter.org/test?do=show&subdo=common&test=file_upload");
        $("input[type='file']").uploadFromClasspath("myfile.txt");
        $("#button").click();
        $("#item").$$("tr").get(0).$$("td").get(1).shouldHave(text("myfile.txt"));

    }

    @Test
    @DisplayName("Скачивание текстового файла и проверка его содержимого")
    void downloadSimpleTextFileTest() throws IOException {

        open("https://filesamples.com/formats/txt");
        File download = $("#output").$$(".btn-primary").get(0).download();
        String fileContent = IOUtils.toString(new FileReader(download));
        assertTrue(fileContent.contains("Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
    }

    @Test
    @DisplayName("Скачивание PDF файла")
    void pdfFileDownloadTest() throws IOException {

        open("https://docs.pexip.com/admin/download_pdf.htm");
        File pdf = $(byText("Pexip Infinity v")).download();
        PDF parsedPdf = new PDF(pdf);
        Assertions.assertEquals(16, parsedPdf.numberOfPages);
    }

    /*@Test
    @DisplayName("Скачивание XLS файла")
    void xlsFileDownloadTest() throws IOException {
        open("http://romashka2008.ru/price");
        File file = $$("a[href*='prajs']")
                .find(text("Скачать Прайс-лист Excel"))
                .download();

        XLS parsedXls = new XLS(file);
        boolean checkPassed = parsedXls.excel
                .getSheetAt(0)
                .getRow(11)
                .getCell(1)
                .getStringCellValue()
                .contains("693010, Сахалинская обл, Южно-Сахалинск г, им Анкудинова Федора Степановича б-р, дом № 15, корпус А");

        assertTrue(checkPassed);
    }

    @Test
    @DisplayName("Парсинг CSV файлов")
    void parseCsvFileTest() throws IOException, CsvException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream("csv.csv");
             Reader reader = new InputStreamReader(is)) {
            CSVReader csvReader = new CSVReader(reader);

            List<String[]> strings = csvReader.readAll();
            assertEquals(3, strings.size());
        }
    }

    @Test
    @DisplayName("Парсинг ZIP файлов")
    void parseZipFileTest() throws IOException, CsvException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream("zip_2MB.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        }
    }*/

}
