package pl.swpws.controller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.swpws.data.repository.Repository;
import pl.swpws.model.SceneName;

public class RootPage implements EventHandler<KeyEvent> {

    final Stage mStage;
    final BorderPane mParent;
    final SceneName mSceneName;
    final Repository mRepository;

    public RootPage(Stage stage, BorderPane parent, SceneName sceneName, Repository repository) {
        mStage = stage;
        mParent = parent;
        mSceneName = sceneName;
        mRepository = repository;
    }

    Node getNodeScene() {
        return null;
    }

    @Override
    public void handle(KeyEvent keyEvent) {

    }

    void goToNextPage() {
        TaskPage.navigateTo(mSceneName);
    }

}
