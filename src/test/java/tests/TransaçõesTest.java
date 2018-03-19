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
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransaçõesTest {
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
        navegador.get("http://desafioqa.marketpay.com.br:9083/desafioqa/login");

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

        //Localizar botãoQA
        WebElement botaoQA = navegador.findElement(By.xpath("//*[@id=\"left-panel\"]/nav/ul[2]/li/a/i"));
        //clicar no botao QA
        botaoQA.click();

        //localizar botão Transações
        WebElement botaoTransacoes = navegador.findElement(By.xpath("//*[@id=\"left-panel\"]/nav/ul[2]/li/ul/li[2]/a"));
        //clicar no botão transações
        botaoTransacoes.click();

    }

    @After
    public void  TearDown(){
        navegador.close();
    }

    @Test
    public void realizarVenda(){

        //localizar botao inlcuirTransacao
        WebElement botaoIncluirTransação = navegador.findElement(By.xpath("//*[@id=\"left-panel\"]/nav/ul[2]/li/ul/li[2]/ul/li[1]/a/span"));
        botaoIncluirTransação.click();

        //localizar caixa de seleçao de cliente
        WebElement campoDeSelecaoCliente = navegador.findElement(By.xpath("//*[@id=\"cliente\"]"));


        //Selecionar cliente que esta na posicao 1 do indice que se inicia em 0
        Select selecionaNome = new Select(campoDeSelecaoCliente);
        selecionaNome.selectByIndex(1);

        //localizar campo de inserção do valor da compra
        WebElement campoValor = navegador.findElement(By.xpath("//*[@id=\"valorTransacao\"]"));
        //inserir valor
        campoValor.sendKeys("1");

        //Localizar botao salvar
        WebElement botaoSalvar = navegador.findElement(By.xpath("//*[@id=\"botaoSalvar\"]"));
        botaoSalvar.click();

        //localizar mensagem temporaria de sucesso da venda
        WebElement mensagemConfirmaçãoDeVenda = navegador.findElement(By.id("alertMessage"));
        System.out.println(mensagemConfirmaçãoDeVenda.getText());

        //Verificação do login com sucesso a través da mensagem de saudação
        assertEquals("×\n" +
                "Venda realizada com sucesso",mensagemConfirmaçãoDeVenda.getText());
    }

    @Test
    public void consultarVenda(){

        //Localizar o botao consultar venda
        WebElement botaoListar = navegador.findElement(By.xpath("//*[@id=\"left-panel\"]/nav/ul[2]/li/ul/li[2]/ul/li[2]/a"));

        //clicar no botao listar
        botaoListar.click();

        //Localizar o botão pesquisar
        WebElement botaoPesquisar = navegador.findElement(By.xpath("//*[@id=\"formListarTransacao\"]/div/div/fieldset[2]/div/div/div/input"));
        //clicar no botão pesquisar
        botaoPesquisar.click();

        //localizar tabela de resultados da pesquisa
        WebElement tabelaDeResultados = navegador.findElement(By.xpath("//*[@id=\"formListarTransacao\"]/div/div/div/table/tbody"));
        String elementos = tabelaDeResultados.getText();
        //imprime no console o resultado de elementos retornados pela pesquisa
        System.out.println(elementos);

        //valida que o resultado não é vazio, caso o contrário, será exibida a mensagem: "Não foi encontrado itens!"
        assertTrue("Não foi encontrado itens!",elementos!=null);


    }

}
