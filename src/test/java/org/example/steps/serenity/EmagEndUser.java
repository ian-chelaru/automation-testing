package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.EmagPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EmagEndUser
{
    EmagPage emagPage;

    @Step
    public void enters(String keyword)
    {
        emagPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search()
    {
        emagPage.lookup_terms();
    }

    @Step
    public void should_see_products(String definition)
    {
        assertThat(emagPage.getProducts(),hasItem(containsString(definition)));
    }

    @Step
    public void is_the_home_page()
    {
        emagPage.open();
    }

    @Step
    public void looks_for(String term)
    {
        enters(term);
        starts_search();
    }

    @Step
    public void should_get_results(String result)
    {
        assertThat(emagPage.getResults(),equalTo(result));
    }

    @Step
    public void filters_price()
    {
        emagPage.filterPriceUnder50();
    }

    @Step
    public void should_not_see_prices_above(int price)
    {
        assertThat(emagPage.countAbovePrice(price),equalTo(0L));
    }

    @Step
    public void change_min_value(String value)
    {
        emagPage.setMinPrice(value);
    }

    @Step
    public void checks_min_value()
    {
        assertThat(emagPage.getMinPrice(),greaterThan(0));
    }
}
