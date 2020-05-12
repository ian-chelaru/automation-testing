package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.EmagEndUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class SearchDdt
{
    @Managed(uniqueSession = true)
    public WebDriver webDriver;

    @Steps
    public EmagEndUser endUser;

    @Test
    public void search_scaun()
    {
        endUser.is_the_home_page();
        endUser.looks_for("scaun");
        endUser.should_see_products("Scaun");
    }

    @Test
    public void invalid_search()
    {
        String invalidInput = "qwertyuiop";
        endUser.is_the_home_page();
        endUser.looks_for(invalidInput);
        endUser.should_get_results("0");
    }

}
