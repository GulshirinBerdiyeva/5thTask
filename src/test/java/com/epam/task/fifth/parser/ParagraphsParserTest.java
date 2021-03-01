package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import com.epam.task.fifth.entity.Lexeme;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ParagraphsParserTest {
    private final String PARAGRAPH = "Keep it simple. Keep it real!";
    private final Component SENTENCE_1 = new Composite(Arrays.asList(
                                                            Lexeme.word("Keep"),
                                                            Lexeme.word("it"),
                                                            Lexeme.word("simple")));
    private final Component SENTENCE_2 = new Composite(Arrays.asList(
                                                            Lexeme.word("Keep"),
                                                            Lexeme.word("it"),
                                                            Lexeme.word("real")));
    private final Composite EXPECTED = new Composite();

    @Test
    public void testParseShouldReturnSentencesWhenParagraphApplied(){
        //given
        EXPECTED.add(SENTENCE_1);
        EXPECTED.add(SENTENCE_2);

        SentencesParser nextParser = Mockito.mock(SentencesParser.class);
        when(nextParser.parse(anyString())).thenReturn(SENTENCE_1, SENTENCE_2);
        ParagraphsParser parser = new ParagraphsParser(nextParser);

        //when
        Component actual = parser.parse(PARAGRAPH);

        //then
        Assert.assertEquals(EXPECTED, actual);
    }

}