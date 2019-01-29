package com.mygdx.yogipogi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.PlayScreen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.audio.Music;

public class YogiPogi extends Game
{
	//Declare variables
	public SpriteBatch batch; //All screens have access to this spritebatch
	public BitmapFont font;
	Texture yogishTexture;
	private Texture dummyMap;
	private Music radio;
	private Sprite yogish;
	private int x = 0, y = 0;
	
	@Override
	public void create () {
		//Load assets	
		batch = new SpriteBatch();
		font = new BitmapFont();
		setScreen(new PlayScreen(this));
		dummyMap = new Texture("SuperMarioBrosMap1-1.png");
		yogishTexture = new Texture("Yogish.jpeg");
		yogish = new Sprite(yogishTexture, x, y, 275, 250);
		yogish.setScale(0.20f);
		radio = Gdx.audio.newMusic(Gdx.files.internal("SylvanEssoRadio.mp3"));
		radio.setVolume(0.5f);
		radio.play();
		radio.setLooping(true);
	}

	@Override
	public void render () {
		super.render();
		//Clear screen
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(dummyMap, 0, 0);
		yogish.draw(batch);
		batch.end();
		
		//Controls
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			x -= 10;
			yogish.setPosition(x, y);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			x +=10;
			yogish.setPosition(x, y);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			y -= 10;
			yogish.setPosition(x, y);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			y += 10;
			yogish.setPosition(x, y);
		} 
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		radio.dispose();
		font.dispose();
		//img.dispose();
	}
}
