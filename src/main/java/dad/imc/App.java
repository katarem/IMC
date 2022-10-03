package dad.imc;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class App extends Application
{
    private TextField peso,altura;
    private Label imc,texto2,texto,pesoLabel,alturaLabel,unidadPeso,unidadAltura;
    private VBox rootPanel;
    private HBox pesoPanel,alturaPanel,imcPanel;
    private Scene scene;
    private DoubleProperty p, a;
    private StringProperty r, i;

    @Override
    public void start(Stage primaryStage) throws Exception {

       
        // creamos cuadros de texto
        peso = new TextField();
        peso.setMaxWidth(50);

        altura = new TextField();
        altura.setMaxWidth(50);

        //creamos etiquetas
        texto = new Label("Bajo Peso | Normal | Sobrepeso | Obeso");
        texto2 = new Label("IMC: ");
        imc = new Label("(peso * altura ^ 2)");
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
        imcPanel.getChildren().addAll(texto2,imc);

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

         //creamos Properties
         p = new SimpleDoubleProperty();

         a = new SimpleDoubleProperty();

         i = new SimpleStringProperty();

         r = new SimpleStringProperty();
         
         peso.textProperty().bindBidirectional(p, new NumberStringConverter());
         altura.textProperty().bindBidirectional(a, new NumberStringConverter());
         a.addListener((o,ov,on) -> calcularPeso());
         p.addListener((o,ov,on) -> calcularPeso());

    }

    private void calcularPeso(){
        if(Integer.parseInt(peso.getText())>0 && Integer.parseInt(altura.getText())>0){       
            DoubleBinding alt = a.divide(100);
            alt = alt.multiply(alt);
            DoubleBinding res = p.divide(alt);
            if(res.doubleValue()<18.5)
                r.set("Bajo peso");
            if((res.doubleValue()>=18.5) && (res.doubleValue()<25))
                r.set("Normal");
            if((res.doubleValue()>=25) && (res.doubleValue()<30))
                r.set("Sobrepeso");
            if(res.doubleValue()>=30)
                r.set("Obeso");
            i.set(String.format("%.2f", res.doubleValue()));
            imc.textProperty().bind(i);
            texto.textProperty().bind(r);
        }
        else{
            imc.setText("(peso * altura ^ 2)");
            texto.setText("Bajo Peso | Normal | Sobrepeso | Obeso");
        }
}

    public static void main( String[] args )
    {
        launch(args);
    }
}
