package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.DomError;
import kz.pompei.electro_schema.dom_core.dom.ParseResult;
import kz.pompei.electro_schema.dom_core.parser.token.ParseError;
import org.testng.annotations.Test;

public class DomParserTest {

  @Test
  public void parse_001() {

    String s = """
      {
        {def id[17] title[hello]
         style display[rect] left[13] top[10] right[10] bottom[10] color[back]
          
          {style display[rect] left[2] top[2] right[2] bottom[2] color[white]
            
            {style display[rect] left[30] top[30] width[100] height[100] color[black]
              {style display[rect] left[2] top[2] right[2] bottom[2] color[white]}
            }
            {style display[rect] left[150] top[30] width[100] height[100] color[black]
              {style display[rect] left[2] top[2] right[2] bottom[2] color[white]}
            }
            {style display[rect] left[150] top[150] width[100] height[100] color[black]
              {style display[rect] left[2] top[2] right[2] bottom[2] color[white]}
            }
            {style display[rect] left[30] top[150] width[100] height[100] color[black]
              {style display[rect] left[2] top[2] right[2] bottom[2] color[white]}
            }
            (This is a text of hello)
            {style display[span] attr id[x][t]
              (world)
            }
            (. The new day begins.)
            
          }
        }
      }
      """;

    doTest(s);
  }

  @Test
  public void parse_002() {

    String s = """
      {def id[17] title[hello]
       style display[rect] left[13] top[10] right[10] bottom[10] color[back]
         {}
         {
            (wow text)
         }
      }
      """;

    doTest(s);
  }

  @Test
  public void parse_003() {
    doTest("{asd[x][y]}");
  }

  @Test
  public void parse_004() {
    doTest("{asd[x] wow}");
  }

  private void doTest(String s) {
    //
    //
    ParseResult parseResult = DomParser.parse(s);
    //
    //

    StringBuilder sb = new StringBuilder();
    parseResult.branch().print(sb);
    System.out.println(sb);

    for (ParseError error : parseResult.parseErrors()) {
      System.out.println("5X0cq1zz31 :: " + error);
    }
    for (DomError error : parseResult.domErrors()) {
      System.out.println("w5Cs29wM6o :: " + error);
    }


  }

}