package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import suporte.Generator;
import suporte.Screenshot;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void  setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\automacao\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver(); //abrir navegador

        // adiciona tempo de espera de 15 segundos para carregar a tela e fazer as verificações dos elementos da tela
        navegador.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        // maximiza a tela
        navegador.manage().window().maximize();

        // abrir pagina de teste
        navegador.get("http://desafioqa.marketpay.com.br:9082/desafioqa/login");


    } @After
    public void  TearDown(){
        navegador.close();
    }



    @Test
    public void realizarLogin(){
        //localizar campo login
        WebElement campoLogin = navegador.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/section[1]/label[2]/input"));
        //inserir usuario
        campoLogin.sendKeys("admin");

        //localizar campo senha
        WebElement campoSenha = navegador.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/section[2]/label[2]/input"));
        //inserir senha
        campoSenha.sendKeys("admin");

        //localizar botao sing in
        WebElement botaoSingIn = navegador.findElement(By.xpath("//*[@id=\"login-form\"]/footer/button"));
        //clicar no botão sing in
        botaoSingIn.click();

        //localizar mensagem bem vindo
        WebElement mensagemBemVindo = navegador.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/h1"));
        //inserir na string a saudação
        String saudacao =mensagemBemVindo.getText();



        String assertionError = null;
        try {

            assertEquals("Bem vindo ao Desafio",saudacao);
        }
        catch (AssertionError ae) {
            Screenshot.tirarPrint(navegador, "C:\\automacao\\evidencias\\"+ Generator.dataHoraParaArquivo() + test.getMethodName()+".png");
            assertionError = ae.toString();
        }

        System.out.println(assertionError);
        //Verificação do login com sucesso a través da mensagem de saudação

        assertEquals("Bem vindo ao Desafio",saudacao);



     }}
