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

public class ClientesTest {
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
    }
    @After
    public void  TearDown(){
        navegador.close();
    }

    @Test
    public void incluirCliente(){
        //Localizar botãoQA
        WebElement botaoQA = navegador.findElement(By.xpath("//*[@id=\"left-panel\"]/nav/ul[2]/li/a/i"));
        //clicar no botao QA
        botaoQA.click();

        //localizar botao clientes
        WebElement botaoCliente = navegador.findElement(By.xpath("//*[@id=\"left-panel\"]/nav/ul[2]/li/ul/li[1]/a/span"));
        //clicar no botao clientes
        botaoCliente.click();

        //localizar botaoIncluir
        WebElement botaoIncluir = navegador.findElement(By.xpath("//*[@id=\"left-panel\"]/nav/ul[2]/li/ul/li[1]/ul/li[1]/a/span"));
        botaoIncluir.click();

        //localizar campo nome
        WebElement campoNome = navegador.findElement(By.xpath("//*[@id=\"nome\"]"));
        //Inserir nome do cliente
        campoNome.sendKeys("Daniel");

        //localizar campo CPF
        WebElement campoCPF = navegador.findElement(By.xpath("//*[@id=\"cpf\"]"));
        //Inserir CPF
        campoCPF.sendKeys("06083421497");

        //localizar campo de seleção ATIVO
        WebElement campoDeSelecaoAtivo = navegador.findElement(By.id("status"));

        //Selecionar opção Ativo?
        new Select(campoDeSelecaoAtivo).selectByValue("false");
        new Select(campoDeSelecaoAtivo).selectByValue("true");

        //localizar campo saldo
        WebElement campoSaldo = navegador.findElement(By.xpath("//*[@id=\"saldoCliente\"]"));
        //inserir saldo
        campoSaldo.sendKeys("10");

        //localizar botao salvar
        WebElement botaoSalvar = navegador.findElement(By.xpath("//*[@id=\"botaoSalvar\"]"));
        //clicar no botao savar
        botaoSalvar.click();

        //localizar mensagem alerta
        WebElement mensagemAlerta = navegador.findElement(By.id("alertMessage"));
        String mensagem = mensagemAlerta.getText();
        System.out.println(mensagem);


        //Verificação do login com sucesso a través da mensagem de saudação
        assertEquals("×\n" +
                "Cliente salvo com sucesso",mensagem);
    }

    @Test
    public void consultarCliente(){
        //Localizar botãoQA
        WebElement botaoQA = navegador.findElement(By.xpath("//*[@id=\"left-panel\"]/nav/ul[2]/li/a/i"));
        //clicar no botao QA
        botaoQA.click();

        //localizar botao clientes
        WebElement botaoCliente = navegador.findElement(By.xpath("//*[@id=\"left-panel\"]/nav/ul[2]/li/ul/li[1]/a/span"));
        //clicar no botao clientes
        botaoCliente.click();

        //localizar botaoListar
        WebElement botaoListar = navegador.findElement(By.xpath("//*[@id=\"left-panel\"]/nav/ul[2]/li/ul/li[1]/ul/li[2]/a/span"));
        //clicar no botao listar
        botaoListar.click();

        //localizar campo de inserção de nome do cliente
        WebElement campoNomePésquisa = navegador.findElement(By.xpath("//*[@id=\"formListarCliente\"]/div/fieldset/div/div/div[1]/input"));
        //insserir nome no campo de pesquisa
        campoNomePésquisa.sendKeys("daniel");

        //localizar campo Data de Validade
        WebElement campoData = navegador.findElement(By.xpath("//*[@id=\"calendario_input\"]"));
        //        //inserir data
        campoData.sendKeys("0022018");

        //localizar botao pesquisar
        WebElement botaoPesquisar = navegador.findElement(By.xpath("//*[@id=\"formListarCliente\"]/div/fieldset/div/div/div[3]/input"));
        botaoPesquisar.click();

        //localizar tabela de resultados
        WebElement tabelaResultados =navegador.findElement(By.xpath("//*[@id=\"formListarCliente\"]/div/div/table/tbody"));
        String elementos = tabelaResultados.getText();

        //imprime no console o resultado de elementos retornados pela pesquisa
        System.out.println(elementos);

        //valida que o resultado não é vazio, caso o contrário, será exibida a mensagem: "Não foi encontrado itens!"
        assertTrue("Não foi encontrado itens!",elementos!=null);

    }
}
