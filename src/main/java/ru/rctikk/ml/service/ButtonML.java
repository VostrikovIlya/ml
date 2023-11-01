package ru.rctikk.ml.service;

import org.deeplearning4j.datasets.iterator.impl.EmnistDataSetIterator;
import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.eclipse.deeplearning4j.resources.utils.EMnistSet;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.learning.config.Nadam;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import ru.rctikk.ml.entity.Button;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ButtonML {
    private ButtonService buttonService;
    final int numRows = 28;
    final int numColumns = 28;
    int outputNum = 2; // number of output classes
    int batchSize = 64; // batch size for each epoch
    int rngSeed = 123; // random number seed for reproducibility
    int numEpochs = 15; // number of epochs to perform
    double rate = 0.0015; // learning rate

    public void compute() throws IOException {

        Set<Button> dataset = new HashSet<>(buttonService.getDateSet());
        DataSetIterator mnistTrain = new EmnistDataSetIterator(EMnistSet.COMPLETE,  rngSeed, true);
        DataSetIterator mnistTest = new MnistDataSetIterator(batchSize, false, rngSeed);

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(rngSeed)
                .activation(Activation.RELU)
                .weightInit(WeightInit.XAVIER)
                .updater(new Nadam())
                .l2(rate * 0.005)
                .list()
                .layer(new DenseLayer.Builder()
                        .nIn(numRows * numColumns)
                        .nOut(500)
                        .build())
                .layer(new DenseLayer.Builder()
                        .nIn(500)
                        .nOut(100)
                        .build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX)
                        .nOut(outputNum)
                        .build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(5));

        model.fit(mnistTrain, numEpochs);


        Evaluation eval = model.evaluate(mnistTest);
    }

}
