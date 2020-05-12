package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DefaultUrl("http://www.emag.ro")
public class EmagPage extends PageObject
{
    @FindBy(name = "query")
    private WebElementFacade searchTerms;

    @FindBy(css = "input.form-control.js-custom-price-min")
    private WebElementFacade minPrice;

    public void enter_keywords(String keyword)
    {
        searchTerms.type(keyword);
    }

    public void lookup_terms()
    {
        searchTerms.sendKeys(Keys.ENTER);
    }

    public List<String> getProducts()
    {
        WebElementFacade productList = find(By.id("card_grid"));
        return productList.findElements(By.className("card-section-mid")).stream().map(WebElement::getText).collect(
                Collectors.toList());
    }

    public String getResults()
    {
        String results = find(By.cssSelector("span.title-phrasing")).getText();
        return results.substring(0,results.indexOf(' '));
    }

    public void filterPriceUnder50()
    {
        WebElementFacade filterPrice = find(By.cssSelector(
                "a[href^=\"https://www.emag.ro/search/pret,intre-0-si-50/scaun?ref=lst_leftbar_6411_0-50\"]"));
        filterPrice.click();
    }

    public Stream<String> getNewPrices()
    {
        WebElementFacade productList = find(By.id("card_grid"));
        return productList.findElements(By.cssSelector("p.product-new-price #text")).stream().map(WebElement::getText);
    }

    public long countAbovePrice(int price)
    {
        return getNewPrices().map(Integer::parseInt).filter(p -> p >= price).count();
    }

    public Integer getMinPrice()
    {
        return Integer.parseInt(minPrice.getValue());
    }

    public void setMinPrice(String price)
    {
        minPrice.clear();
        minPrice.type(price);
    }
}
