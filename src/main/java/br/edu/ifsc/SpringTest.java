package br.edu.ifsc;

import br.edu.ifsc.input.Dados;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

@SpringBootApplication
public class SpringTest  {

    public static void main(String[] args) {
        SpringApplication.run(SpringTest.class, args);
    }

}
