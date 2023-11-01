package ru.rctikk.ml.service;

public class ButtonML {
    final int numRows = 28;
    final int numColumns = 28;
    int outputNum = 2; // number of output classes
    int batchSize = 64; // batch size for each epoch
    int rngSeed = 123; // random number seed for reproducibility
    int numEpochs = 15; // number of epochs to perform
    double rate = 0.0015; // learning rate

    public void compute() {

        DataSetIterator mnistTrain = new MnistDataSetIterator(batchSize, true, rngSeed);
        DataSetIterator mnistTest = new MnistDataSetIterator(batchSize, false, rngSeed);

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(rngSeed) //include a random seed for reproducibility
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
                .layer(new OutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX)
                        .nOut(outputNum)
                        .build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(5));

        log.info("Train model....");
        model.fit(mnistTrain, numEpochs);

        log.info("Evaluate model....");
        Evaluation eval = model.evaluate(mnistTest);

        log.info(eval.stats());
        log.info("****************Example finished********************");

    }

}
