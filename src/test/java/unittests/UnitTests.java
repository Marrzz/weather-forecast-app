package unittests;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import exception.WrongInputException;
import helpers.CurrentReportFormatter;
import org.junit.Test;

public class UnitTests {

    @Test
    public void TestShouldReturnCityNameWithFirstLetterUpperCase_whenInputIsAllLowerCase() throws WrongInputException {

        String name = "tallinn";

        String result = CurrentReportFormatter.processInput(name);

        assertThat(result).isEqualTo("Tallinn");

    }

    @Test
    public void shouldThrowAnException_WhenInputContainsNumbers() throws WrongInputException {

        String name = "10284";

        Exception e = assertThrows(WrongInputException.class,() -> CurrentReportFormatter.processInput(name));

        assertThat(e.getMessage()).isEqualTo("City name should contain only letters!");
    }

    @Test
    public void shouldCapitalizeFirstLettersOfAllWords_WhenInputContainsMultipleWords() throws WrongInputException {

        String name = "new york";

        System.out.println(CurrentReportFormatter.processInput(name));
    }


}
