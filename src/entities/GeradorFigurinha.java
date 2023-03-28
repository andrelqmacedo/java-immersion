package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;


public class GeradorFigurinha {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
        /*leitura da imagem
        InputStream inputStream =
                      new FileInputStream(new File("entrada/filme.jpg"));
        InputStream inputStream =
                new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_2.jpg").openStream();*/
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //cria nova imagem em memoria com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original para nova imagem (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        //escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 200, novaAltura - 100);

        //escrever a nova img em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }
}
