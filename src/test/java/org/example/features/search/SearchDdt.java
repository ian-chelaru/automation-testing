package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.example.steps.serenity.EmagEndUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
//@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/EmagTestData.csv")
public class SearchDdt
{
    @Managed(uniqueSession = true)
    public WebDriver webDriver;

    public String name;
    public String products;

    @Qualifier
    public String getQualifier()
    {
        return name;
    }

    @Steps
    public EmagEndUser endUser;

    @Test
    public void search_scaun()
    {
        endUser.is_the_home_page();
        endUser.looks_for("scaun");
        endUser.should_see_products("Scaun");
    }

//    @Test
//    public void search_scaun()
//    {
//        endUser.is_the_home_page();
//        endUser.looks_for(getName());
//        endUser.should_see_products(getProducts());
//    }

    @Test
    public void invalid_search()
    {
        String invalidInput = "qwertyuiop";
        endUser.is_the_home_page();
        endUser.looks_for(invalidInput);
        endUser.should_get_results("0");
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getProducts()
    {
        return products;
    }

    public void setProducts(String products)
    {
        this.products = products;
    }
}
