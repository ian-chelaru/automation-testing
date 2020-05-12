package org.example.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;
import java.util.stream.Collectors;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;

import java.util.List;

@DefaultUrl("http://www.emag.ro/")
//@DefaultUrl("http://en.wiktionary.org/wiki/Wiktionary")
public class DictionaryPage extends PageObject {

//    @FindBy(name="search")
//    private WebElementFacade searchTerms;

    @FindBy(name="query")
    private WebElementFacade searchTerms;

    @FindBy(name="go")
    private WebElementFacade lookupButton;

    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

//    public void lookup_terms() {
//        lookupButton.click();
//    }

    public void lookup_terms() {
        searchTerms.sendKeys(Keys.ENTER);
    }

    public List<String> getDefinitions() {
        WebElementFacade definitionList = find(By.id("card_grid"));
        return definitionList.findElements(By.className("card-section-mid")).stream()
                .map( element -> element.getText() )
                .collect(Collectors.toList());
    }

//    public List<String> getDefinitions() {
//        WebElementFacade definitionList = find(By.tagName("ol"));
//        return definitionList.findElements(By.tagName("li")).stream()
//                .map( element -> element.getText() )
//                .collect(Collectors.toList());
//    }
}