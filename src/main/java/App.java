import models.ChangeMachine;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

/**
 * Created by Guest on 8/7/17.
 */
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public"); //this line has changed!
        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/change_result", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String money = request.queryParams("money");
            model.put("money", money);
            float totalCash2 = parseFloat(money);
            ChangeMachine newMachine = new ChangeMachine();
            String Result = newMachine.makeChange(totalCash2);
            model.put("changeResult", Result);

            return new ModelAndView(model, "change_result.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
