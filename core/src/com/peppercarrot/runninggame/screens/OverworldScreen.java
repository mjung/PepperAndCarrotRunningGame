package com.peppercarrot.runninggame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.peppercarrot.runninggame.overworld.OverworldLayout;
import com.peppercarrot.runninggame.overworld.OverworldLayoutLoader;
import com.peppercarrot.runninggame.overworld.OverworldStage;

public class OverworldScreen extends ScreenAdapter {
	private static final AssetManager assetManager = new AssetManager();

	private final OverworldStage stage;

	public OverworldScreen() {
		final OverworldLayout layout = loadLayout("pac.ol");
		stage = new OverworldStage(layout);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		stage.render(delta);

		if (Gdx.input.isKeyJustPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	private OverworldLayout loadLayout(String layoutName) {
		if (!assetManager.isLoaded(layoutName)) {
			assetManager.setLoader(OverworldLayout.class, new OverworldLayoutLoader());
			assetManager.load("pac.ol", OverworldLayout.class);
			assetManager.finishLoading();
		}

		return assetManager.get(layoutName);
	}
}
