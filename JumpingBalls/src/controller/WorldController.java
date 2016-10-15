package controller;

import model.BallModel;
import model.ObstacleModel;
import model.WallModel;
import model.WorldModel;
import org.jbox2d.common.Vec2;
import utils.Config;
import view.ObstacleView;
import view.WallView;
import view.WorldView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by nikita.kuzin on 10/15/16.
 */
public class WorldController {

    private WorldModel m_model;
    private WorldView m_view;
    private Config m_config;

    public WorldController(WorldModel worldModel, WorldView worldView, Config config) {
        m_model = worldModel;
        m_view = worldView;
        m_config = config;
        init();
        m_view.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                System.out.println();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                Vec2 position = m_model.getCannonModel().getPosition();
                double angrad = (mouseEvent.getY() - position.y) / (Math.sqrt(Math.pow(position.x - mouseEvent.getX(), 2) + Math.pow(position.y - mouseEvent.getY(), 2)));
                double rotation = Math.toDegrees(angrad);
                m_model.getCannonModel().setRotation((float) rotation);
            }

        });

    }

    private void init() {
        List<ObstacleModel> obstacleModels = m_model.getObstacleModels();
        List<ObstacleView> obstacleViews = m_view.getObstacleViews();
        for (int i = 0; i < obstacleModels.size(); i++) {
            obstacleModels.get(i).addObserver(obstacleViews.get(i));
        }

        List<WallModel> wallModels = m_model.getWallModels();
        List<WallView> wallViews = m_view.getWallViews();
        for (int i = 0; i < wallModels.size(); i++) {
            wallModels.get(i).addObserver(wallViews.get(i));
        }
        m_model.getCannonModel().addObserver(m_view.getCannonView());
    }

    public void sumulate() {
        while (true) {
            update();
        }
    }

    public void update() {
        WorldModel.world.step(m_config.FRAME_RATE, m_config.VELOCITY_ITERATIONS, m_config.POSITION_ITERATIONS);
        m_model.getCannonModel().notifyObservers();
        for (ObstacleModel obstacleModel : m_model.getObstacleModels()) {
            obstacleModel.notifyObservers();
        }
        for (WallModel wallModel : m_model.getWallModels()) {
            wallModel.notifyObservers();
        }
        for (BallModel ballModel : m_model.getBalls()) {
            ballModel.notifyObservers();
        }
        m_view.display();

    }
}
