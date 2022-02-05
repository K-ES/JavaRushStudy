package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?text=java+%s&page=%d";
    // со страницей ниже ничего не работает готовое решение от JR
//    private static final String URL_FORMAT = "https://javarush.ru/testdata/big28data.html?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> allVacancies = new ArrayList<>();

        int page = 0;
        try {
            do {
                if (page == 40) break;
                Document doc = getDocument(searchString, page);

                System.out.println("Попытка найти Лист");
//                Elements vacanciesHtmlList = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                Elements vacanciesHtmlList = doc.getElementsByClass("vacancy-serp-item");
                System.out.println("Количество элементов в Листе: " + vacanciesHtmlList.size());

                if (vacanciesHtmlList.isEmpty()) break;

                for (Element element : vacanciesHtmlList) {
                    Elements links = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title");
                    Elements locations = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address");
                    Elements companyName = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer");
                    Elements salary = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation");

                    Vacancy vacancy = new Vacancy();
                    vacancy.setSiteName("hh.ru");
                    vacancy.setTitle(links.get(0).text());
                    vacancy.setUrl(links.get(0).attr("href"));
                    vacancy.setCity(locations.get(0).text());
                    try {
                        vacancy.setCompanyName(companyName.get(0).text());
                    } catch (IndexOutOfBoundsException e) {
                    }
                    vacancy.setSalary(salary.size() > 0 ? salary.get(0).text() : "");

                    allVacancies.add(vacancy);
                }

                page++;
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allVacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        System.out.println(String.format(URL_FORMAT, searchString, page));
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                .referrer("https://hh.ru/")
                .get();
    }
}