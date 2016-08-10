package br.com.caelum.vraptor.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.CaixaDao;
import br.com.caelum.vraptor.dao.CarrinhoDao;
import br.com.caelum.vraptor.dao.ClienteDao;
import br.com.caelum.vraptor.dao.HistoricoCaixaDao;
import br.com.caelum.vraptor.dao.ProdutoDao;
import br.com.caelum.vraptor.dao.PromocaoDao;
import br.com.caelum.vraptor.model.Caixa;
import br.com.caelum.vraptor.model.Carrinho;
import br.com.caelum.vraptor.model.Cartao;
import br.com.caelum.vraptor.model.Cliente;
import br.com.caelum.vraptor.model.HistoricoCaixa;
import br.com.caelum.vraptor.model.Numero;
import br.com.caelum.vraptor.model.Numero_Produto;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.model.Promocao;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class PagamentoController {

	private final Validator validator;

	private final ClienteLogado clienteLogado;

	private final Result result;

	private final HttpServletRequest request;

	private final HttpServletResponse response;
	
	

	@Inject
	public PagamentoController(Validator validator, ClienteLogado clienteLogado, Result result,
			HttpServletRequest request, HttpServletResponse response) {
		super();
		this.validator = validator;
		this.clienteLogado = clienteLogado;
		this.result = result;
		this.request = request;
		this.response = response;
	}

	@Deprecated
	PagamentoController() {
		this(null, null, null, null, null);
	}
	
	@Post("/CartaoCredito")
	public void cartao(Cartao cartao){
		System.out.println(cartao.toString());
		BigDecimal compara1=Total.getTotal();
		BigDecimal compara2=new BigDecimal(0);
		if(compara1.compareTo(compara2) == 1){
			Promocao promocao = new Promocao();
			promocao.setCodigo(4011956);
			PromocaoDao promocaoDao = new PromocaoDao();

			promocao = promocaoDao.busca(promocao);
			BigDecimal total = Total.getTotal();

			BigDecimal porcento = promocao.getPromocao();
			BigDecimal desconto = total.divide(porcento);
			BigDecimal totalDesconto = total.subtract(desconto);
			
			Cliente cliente= clienteLogado.getCliente();
			System.out.println(cliente.toString());
			ClienteDao dao = new ClienteDao();
			cliente= dao.pega(cliente);
			Carrinho carrinho = new Carrinho();
			CarrinhoDao carrinhoDao = new CarrinhoDao();
			List<Carrinho> list = new ArrayList<Carrinho>();
			list = carrinhoDao.listaCliente(cliente);
			//Aqui atualiza caixa e faz historico
			
			
			for (Carrinho car : list) {
				//####################################Atualizou quantidade####################################
				Produto produto= new Produto();
				Numero numero= new Numero();
				Numero_Produto numProd= new Numero_Produto();
				
				ProdutoDao produtoDao= new ProdutoDao();
				
				numero.setNumero(car.getNumero());
				numero= produtoDao.buscaNumeroNumero(numero);
				
				produto.setId(car.getIdproduto());
				produto= produtoDao.busca(produto);
				
				numProd=produtoDao.NumeroProduto(numero.getId(), produto.getCodigo());
				numProd.setQuantidade(numProd.getQuantidade()-car.getQuantidade());
				if(produtoDao.atualizarQuantidade(numProd)){
					System.out.println("####################################Atualizou quantidade do numprod####################################");
				}
				//####################################Atualizou quantidade####################################
				//####################################Atualizar Caixa####################################
				Caixa caixa= new Caixa();
				CaixaDao caixaDao = new CaixaDao();
				caixa.setCodigo(1);
				caixa = caixaDao.buscaCaixa(caixa);
				caixa.setCredito(totalDesconto.add(caixa.getCredito()));
				caixaDao.atualizar(caixa);
				//####################################Atualizar Caixa####################################
				//####################################Atualizar Historico####################################
				HistoricoCaixa historico= new HistoricoCaixa();
				HistoricoCaixaDao historicoDao= new HistoricoCaixaDao();
				Calendar calendario = Calendar.getInstance();
				Date dd = new Date();
				calendario.setTime(dd);
				historico.setData(calendario);
				historico.setIdCliente(cliente.getId());
				historico.setIdProduto(produto.getId());
				historico.setQuantidade(car.getQuantidade());
				historico.setTotal(totalDesconto);
				historico.setValor(produto.getValor_venda());
				historico.setMotivo("VENDA");
				
				historicoDao.adiciona(historico);
				//####################################Atualizar Historico####################################
				
				carrinhoDao.remove(car);
			}
			Total.setTotal(new BigDecimal(0));
			result.include("total", Total.getTotal());
			
			List<String> datas = new ArrayList<String>();
			String hoje;
	        Date d = new Date();
	        Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.set(Calendar.YEAR, c.get(Calendar.YEAR));
			hoje=new SimpleDateFormat("yy").format(c.getTime());
			datas.add(hoje);
			for(int i=0;i<12;i++){
				c.set(Calendar.YEAR, c.get(Calendar.YEAR)+1);
				hoje=new SimpleDateFormat("yy").format(c.getTime());
				datas.add(hoje);
			}
			
			List<Cliente> list1 = new ArrayList<Cliente>();
			Cliente cliente1= clienteLogado.getCliente();
			System.out.println(cliente1.toString());
			ClienteDao dao1 = new ClienteDao();
			cliente1= dao1.pega(cliente1);
			list1.add(cliente1);
			result.include("ano", datas);
			result.include("listaCliente", list);
			result.include("total", Total.getTotal());
			
			result.redirectTo(CarrinhoController.class).close();
			
		}
		else{
			List<String> datas = new ArrayList<String>();
			String hoje;
	        Date d = new Date();
	        Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.set(Calendar.YEAR, c.get(Calendar.YEAR));
			hoje=new SimpleDateFormat("yy").format(c.getTime());
			datas.add(hoje);
			for(int i=0;i<12;i++){
				c.set(Calendar.YEAR, c.get(Calendar.YEAR)+1);
				hoje=new SimpleDateFormat("yy").format(c.getTime());
				datas.add(hoje);
			}
			
			List<Cliente> list = new ArrayList<Cliente>();
			Cliente cliente= clienteLogado.getCliente();
			System.out.println(cliente.toString());
			ClienteDao dao = new ClienteDao();
			cliente= dao.pega(cliente);
			list.add(cliente);
			result.include("ano", datas);
			result.include("listaCliente", list);
			result.include("total", Total.getTotal());
			validator.add(new I18nMessage("boleto", "carrinho.vazio"));
			validator.onErrorUsePageOf(CarrinhoController.class).close();
		}
	}
	@Path("/GeraBoleto")
	public void boleto() throws IOException {
		
		new File("C:\\boletos").mkdirs();
		BigDecimal compara1=Total.getTotal();
		BigDecimal compara2=new BigDecimal(0);
		if(compara1.compareTo(compara2) == 1)
		{
			Promocao promocao = new Promocao();
			promocao.setCodigo(4011956);
			PromocaoDao promocaoDao = new PromocaoDao();

			promocao = promocaoDao.busca(promocao);
			BigDecimal total = Total.getTotal();

			BigDecimal porcento = promocao.getPromocao();
			BigDecimal desconto = total.divide(porcento);
			BigDecimal totalDesconto = total.subtract(desconto);
			
			Date d = new Date();

			Calendar c = Calendar.getInstance();
			c.setTime(d);
			Calendar atual = Calendar.getInstance();
			atual.setTime(d);

			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 10);
			c.set(Calendar.MONTH, c.get(Calendar.MONTH));
			c.set(Calendar.YEAR, c.get(Calendar.YEAR));
			String hoje=new SimpleDateFormat("dd/MM/yyyy").format(atual.getTime());
			String vencimento=new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
			

			Document document = new Document();
			try {

				Font font1 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
				Font font2 = new Font(Font.FontFamily.COURIER, 10, Font.ITALIC | Font.UNDERLINE);
				Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 10);

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\boletos\\pagamento.pdf"));

				document.open();

				PdfPTable table = new PdfPTable(5);
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

				PdfPCell cabecalho = new PdfPCell(new Paragraph(
						"|104-0|            " + "97802016158837888455136418455896221454522334567914534121325237566659",
						font3));

				cabecalho.setColspan(5);
				table.addCell(cabecalho);
				PdfPCell pdf1 = new PdfPCell(
						new Paragraph("Local de Pagamento \n" + "Pagável em qualquer banco até o vencimento", font3));

				PdfPCell pdf2 = new PdfPCell(new Paragraph("Vencimento \n " + vencimento, font3));
				pdf1.setColspan(3);
				pdf2.setColspan(2);
				table.addCell(pdf1);
				table.addCell(pdf2);

				PdfPCell pdf3 = new PdfPCell(new Paragraph("Beneficiário \nNewShoes Ltda.", font3));
				PdfPCell pdf4 = new PdfPCell(new Paragraph("Agência/Código do Benificiário \n" + "8552/12036-8", font3));
				pdf3.setColspan(3);
				pdf4.setColspan(2);
				table.addCell(pdf3);
				table.addCell(pdf4);
				
				
				
				PdfPCell pdf5 = new PdfPCell(new Paragraph("Data do Documento \n" + hoje, font3));
				PdfPCell pdf6 = new PdfPCell(new Paragraph("N° Documento", font3));
				PdfPCell pdf7 = new PdfPCell(new Paragraph("Tipo Doc.", font3));
				PdfPCell pdf8 = new PdfPCell(new Paragraph("Aceite", font3));
				PdfPCell pdf9 = new PdfPCell(new Paragraph("Data de Processamento \n"+hoje+"", font3));
				table.addCell(pdf5);
				table.addCell(pdf6);
				table.addCell(pdf7);
				table.addCell(pdf8);
				table.addCell(pdf9);

				PdfPCell pdf10 = new PdfPCell(new Paragraph("Instruções \n\n" + "Não receber após o vencimento.\n\n"
						+ "Conceder multa de 2% após o dia "+vencimento+"\n" + "", font3));
				pdf10.setRowspan(5);
				pdf10.setColspan(3);
				PdfPCell pdf11 = new PdfPCell(new Paragraph("(=)Valor documento\n" + " R$" + total, font3));
				PdfPCell pdf12 = new PdfPCell(new Paragraph("(-)Descontos/Abatimentos\n" + " R$" + desconto, font3));
				PdfPCell pdf13 = new PdfPCell(new Paragraph("(-)Outras deduções\n" + " ", font3));
				PdfPCell pdf14 = new PdfPCell(new Paragraph("(+)Mora/Multa\n" + " ", font3));
				PdfPCell pdf15 = new PdfPCell(new Paragraph("(=)Valor Cobrado\n" + " R$" + totalDesconto, font3));
				pdf11.setColspan(2);
				pdf12.setColspan(2);
				pdf13.setColspan(2);
				pdf14.setColspan(2);
				pdf15.setColspan(2);

				table.addCell(pdf10);
				table.addCell(pdf11);
				table.addCell(pdf12);
				table.addCell(pdf13);
				table.addCell(pdf14);
				table.addCell(pdf15);

				PdfPCell pdf16 = new PdfPCell(new Paragraph("Sacado\n" + "NewShoes Ltda. Cnpj: 00.000.000/0001-91\n"
						+ "Avenida Das Fontes 1777 11 Andar\n" + "Parque Das Fontes - São Paulo/SP - Cep: 13950-000",
						font3));
				pdf16.setColspan(5);
				table.addCell(pdf16);
				document.add(table);
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));

				PdfContentByte cb = writer.getDirectContent();

				BarcodeEAN codeEAN1 = new BarcodeEAN();
				BarcodeEAN codeEAN2 = new BarcodeEAN();
				BarcodeEAN codeEAN3 = new BarcodeEAN();
				BarcodeEAN codeEAN4 = new BarcodeEAN();
				BarcodeEAN codeEAN5 = new BarcodeEAN();

				codeEAN1.setCodeType(codeEAN1.EAN13);
				codeEAN1.setCode("9780201615883");
				Image imageEAN1 = codeEAN1.createImageWithBarcode(cb, null, null);

				codeEAN2.setCodeType(codeEAN2.EAN13);
				codeEAN2.setCode("7888455136418");
				Image imageEAN2 = codeEAN2.createImageWithBarcode(cb, null, null);

				codeEAN3.setCodeType(codeEAN3.EAN13);
				codeEAN3.setCode("4558962214545");
				Image imageEAN3 = codeEAN3.createImageWithBarcode(cb, null, null);

				codeEAN4.setCodeType(codeEAN4.EAN13);
				codeEAN4.setCode("22334567914534");
				Image imageEAN4 = codeEAN4.createImageWithBarcode(cb, null, null);

				codeEAN5.setCodeType(codeEAN5.EAN13);
				codeEAN5.setCode("121325237566659");
				Image imageEAN5 = codeEAN5.createImageWithBarcode(cb, null, null);
				document.add(new Phrase("               "));
				document.add(new Phrase(new Chunk(imageEAN1, 0, 0)));
				document.add(new Phrase(new Chunk(imageEAN2, 0, 0)));
				document.add(new Phrase(new Chunk(imageEAN3, 0, 0)));
				document.add(new Phrase(new Chunk(imageEAN4, 0, 0)));
				document.add(new Phrase(new Chunk(imageEAN5, 0, 0)));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));

				document.add(new Paragraph("               --------------------------------------------" + "destaque aqui"
						+ "--------------------------------------------"));

				System.out.println("Gerado");
			} catch (DocumentException de) {
				System.out.println("Erro" + de);
				System.err.println(de.getMessage());
			} catch (IOException ioe) {
				System.out.println("Erro" + ioe);
				System.err.println(ioe.getMessage());
			}
			document.close();
			/** ---------------------------------------- */
			/** Código para mandar o pdf para download */
			if (1 == 1) {
				System.out.println("Passa aqui ###########################################33");
		        File arquivo = new File("C:\\boletos\\pagamento.pdf");
		        String nom = arquivo.getName();
		        int tamanho = (int) arquivo.length();
		        java.nio.file.Path path = arquivo.toPath();

		        response.setContentType("pdf"); // tipo do conteúdo
		        response.setContentLength(tamanho); // opcional
		        response.setHeader("Content-Disposition", "attachment; filename=\"" + nom + "\"");

		        OutputStream output = null;
		        output = response.getOutputStream();
		        Files.copy(path, output);
				
				
				
			}
			Cliente cliente= clienteLogado.getCliente();
			System.out.println(cliente.toString());
			ClienteDao dao = new ClienteDao();
			cliente= dao.pega(cliente);
			Carrinho carrinho = new Carrinho();
			CarrinhoDao carrinhoDao = new CarrinhoDao();
			List<Carrinho> list = new ArrayList<Carrinho>();
			list = carrinhoDao.listaCliente(cliente);
			//Aqui atualiza caixa e faz historico
			
			
			for (Carrinho car : list) {
				//####################################Atualizou quantidade####################################
				Produto produto= new Produto();
				Numero numero= new Numero();
				Numero_Produto numProd= new Numero_Produto();
				
				ProdutoDao produtoDao= new ProdutoDao();
				
				numero.setNumero(car.getNumero());
				numero= produtoDao.buscaNumeroNumero(numero);
				
				produto.setId(car.getIdproduto());
				produto= produtoDao.busca(produto);
				
				numProd=produtoDao.NumeroProduto(numero.getId(), produto.getCodigo());
				numProd.setQuantidade(numProd.getQuantidade()-car.getQuantidade());
				if(produtoDao.atualizarQuantidade(numProd)){
					System.out.println("####################################Atualizou quantidade do numprod####################################");
				}
				//####################################Atualizou quantidade####################################
				//####################################Atualizar Caixa####################################
				Caixa caixa= new Caixa();
				CaixaDao caixaDao = new CaixaDao();
				caixa.setCodigo(1);
				caixa = caixaDao.buscaCaixa(caixa);
				caixa.setDinheiro(totalDesconto.add(caixa.getDinheiro()));
				caixaDao.atualizar(caixa);
				//####################################Atualizar Caixa####################################
				//####################################Atualizar Historico####################################
				HistoricoCaixa historico= new HistoricoCaixa();
				HistoricoCaixaDao historicoDao= new HistoricoCaixaDao();
				Calendar calendario = Calendar.getInstance();
				Date dd = new Date();
				calendario.setTime(dd);
				historico.setData(calendario);
				historico.setIdCliente(cliente.getId());
				historico.setIdProduto(produto.getId());
				historico.setQuantidade(car.getQuantidade());
				historico.setTotal(totalDesconto);
				historico.setValor(produto.getValor_venda());
				historico.setMotivo("VENDA");
				
				historicoDao.adiciona(historico);
				//####################################Atualizar Historico####################################
				
				carrinhoDao.remove(car);
			}
			Total.setTotal(new BigDecimal(0));
			result.include("total", Total.getTotal());
			List<String> datas1 = new ArrayList<String>();
			String hoje1;
	        Date d1 = new Date();
	        Calendar c1 = Calendar.getInstance();
			c1.setTime(d1);
			c1.set(Calendar.YEAR, c1.get(Calendar.YEAR));
			hoje1=new SimpleDateFormat("yy").format(c1.getTime());
			datas1.add(hoje1);
			for(int i=0;i<12;i++){
				c1.set(Calendar.YEAR, c1.get(Calendar.YEAR)+1);
				hoje1=new SimpleDateFormat("yy").format(c1.getTime());
				datas1.add(hoje1);
			}
			
			List<Cliente> list2 = new ArrayList<Cliente>();
			Cliente cliente2= clienteLogado.getCliente();
			System.out.println(cliente2.toString());
			ClienteDao dao2 = new ClienteDao();
			cliente2= dao2.pega(cliente2);
			list2.add(cliente2);
			result.include("ano", datas1);
			result.include("listaCliente", list2);
			result.include("total", Total.getTotal());
//			result.redirectTo(CarrinhoController.class).close();
		}else{
			List<String> datas = new ArrayList<String>();
			String hoje;
	        Date d = new Date();
	        Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.set(Calendar.YEAR, c.get(Calendar.YEAR));
			hoje=new SimpleDateFormat("yy").format(c.getTime());
			datas.add(hoje);
			for(int i=0;i<12;i++){
				c.set(Calendar.YEAR, c.get(Calendar.YEAR)+1);
				hoje=new SimpleDateFormat("yy").format(c.getTime());
				datas.add(hoje);
			}
			
			List<Cliente> list = new ArrayList<Cliente>();
			Cliente cliente= clienteLogado.getCliente();
			System.out.println(cliente.toString());
			ClienteDao dao = new ClienteDao();
			cliente= dao.pega(cliente);
			list.add(cliente);
			result.include("ano", datas);
			result.include("listaCliente", list);
			result.include("total", Total.getTotal());
			validator.add(new I18nMessage("boleto", "carrinho.vazio"));
			validator.onErrorUsePageOf(CarrinhoController.class).close();
		}
		
		
	}

	
	public void close() {
		
	}


}
