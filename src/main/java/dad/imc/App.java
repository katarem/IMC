package dad.imc;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application
{
    private TextField peso,altura;
    private Label imc,texto,pesoLabel,alturaLabel,unidadPeso,unidadAltura;
    private VBox rootPanel;
    private HBox pesoPanel,alturaPanel,imcPanel;
    private Scene scene;
    private DoubleProperty p, a;

    @Override
    public void start(Stage primaryStage) throws Exception {

        //creamos Properties
        p = new SimpleDoubleProperty(0);
        a = new SimpleDoubleProperty(0);
    //    p.addListener(e -> calcularPeso(peso.getText(), altura.getText()));
    //    a.addListener(e -> calcularPeso(peso.getText(), altura.getText()));
        // creamos cuadros de texto

       p.set(Double.parseDouble(peso.getText()));
       a.set(Double.parseDouble(altura.getText()));
       a.bind(altura);

        peso = new TextField();
        peso.setMaxWidth(50);
        altura = new TextField();
        altura.setMaxWidth(50);

        //creamos etiquetas
        texto = new Label("Bajo Peso | Normal | Sobrepeso | Obeso");
        imc = new Label("IMC: (peso * altura ^ 2)");
        pesoLabel = new Label("Peso:");
        unidadAltura = new Label("cm");
        unidadPeso = new Label("kg");
        alturaLabel = new Label("Altura:");
        //creamos paneles horizontales
        pesoPanel = new HBox();
        pesoPanel.setAlignment(Pos.CENTER);
        pesoPanel.setFillHeight(false);
        pesoPanel.setSpacing(5);
        pesoPanel.getChildren().addAll(pesoLabel,peso,unidadPeso);

        alturaPanel = new HBox();
        alturaPanel.setAlignment(Pos.CENTER);
        alturaPanel.setFillHeight(false);
        alturaPanel.setSpacing(5);
        alturaPanel.getChildren().addAll(alturaLabel,altura,unidadAltura);

        imcPanel = new HBox();
        imcPanel.setAlignment(Pos.CENTER);
        imcPanel.setFillHeight(false);
        imcPanel.setSpacing(5);
        imcPanel.getChildren().addAll(imc);

        //creamos el panel con disposicion vertical
        rootPanel = new VBox();
        rootPanel.setFillWidth(false);
        rootPanel.setSpacing(15);
        rootPanel.getChildren().addAll(pesoPanel,alturaPanel,imcPanel,texto);
        rootPanel.setAlignment(Pos.CENTER);

        //Escena
        scene = new Scene(rootPanel,320,200);

        //Stage
        primaryStage.setTitle("Calculadora de IMC");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void calcularPeso(DoubleProperty peso, DoubleProperty alt){
        double a = alt.getValue();
        double p = peso.getValue();
        double num;
        num = (p/(Math.pow(a, 2)));
        imc.setText(String.format("IMC: %.2f", num));

        if(num<18.5)
            texto.setText("Bajo peso");
        if((num>=18.5) && (num<25))
            texto.setText("Normal");
        if((num>=25) && (num<30))
            texto.setText("Sobrepeso");
        if(num>=30)
            texto.setText("Obeso");
    }
    public static void main( String[] args )
    {
        launch(args);
    }
}
