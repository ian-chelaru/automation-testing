package org.example.features.filter;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.EmagEndUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class FilterDdt
{
    @Managed(uniqueSession = true)
    public WebDriver webDriver;

    @Steps
    public EmagEndUser endUser;

    private void scrollByPixels(int xPixels, int yPixels)
    {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        String script = "window.scrollBy(" + xPixels + "," + yPixels + ")";
        jse.executeScript(script);
    }

    @Test
    public void filter_by_price()
    {
        endUser.is_the_home_page();
        endUser.looks_for("scaun");
        scrollByPixels(0, 600);
        endUser.filters_price();
        endUser.should_not_see_prices_above(50);
    }

    @Test
    public void invalid_filter()
    {
        endUser.is_the_home_page();
        endUser.looks_for("scaun");
        scrollByPixels(0, 800);
        endUser.change_min_value("-1");
        endUser.starts_search();
        scrollByPixels(0, 800);
        endUser.checks_min_value();
    }
}
