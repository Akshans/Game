package com.mygdx.yogipogi;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;

import Screens.PlayScreen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.audio.Music;

public class YogiPogi extends Game
{
	//Declare variables
	static final int WORLD_WIDTH = 100;
	static final int WORLD_HEIGHT = 100;
	//static final loglevel = 
	
	private OrthographicCamera camera;
	private float rotationSpeed;
	
	public SpriteBatch batch; //All screens have access to this spritebatch
	
	public BitmapFont font;
	Texture yogishTexture;
	private Texture dummyMapTexture;
	private Music radio;
	private Sprite yogish;
	private Sprite dummyMap;
	private int x = 0, y = 0;
	private int x1 = 0, y1 = 0;
	private int rightTrigger, leftTrigger;
	
	@Override
	public void create () {
		//Load assets	
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		rotationSpeed = 0.5f;
		dummyMapTexture = new Texture("SuperMarioBrosMap1-1.png");
		dummyMap = new Sprite(dummyMapTexture);
		dummyMap.setPosition(0, 0);
		//dummyMap.setSize(WORLD_WIDTH, WORLD_HEIGHT);
		float w = Gdx.graphics.getWidth(); //Width of screen in pixels
		float h = Gdx.graphics.getHeight(); //Height of screen in pixels
		
		rightTrigger =(int) (0.75*Gdx.graphics.getWidth());
		//leftTrigger =(int) 0.25*Gdx.graphics.getWidth();
		leftTrigger=0;
		
		
		System.out.println("Width: " + w);
		System.out.println("Height: " + h);
		
		camera = new OrthographicCamera(w, h);
		camera.position.set(w/ 2f, h / 2f, 0);
		
		Gdx.app.debug(Float.toString(camera.viewportWidth), "Viewport width");
		//System.out.println("Viewport width: " + w);
		//System.out.println("Viewport ");
		camera.update();                                                            

		batch = new SpriteBatch();
		font = new BitmapFont();
		//setScreen(new PlayScreen(this));
		
		yogishTexture = new Texture("Yogish.jpeg");
		yogish = new Sprite(yogishTexture, x, y, 275, 250);
		
		yogish.setScale(0.20f);
		radio = Gdx.audio.newMusic(Gdx.files.internal("SylvanEssoRadio.mp3"));
		radio.setVolume(0.5f);
		radio.play();
		radio.setLooping(true);
	}

	@Override
	public void render () 
	{
		//super.render();
		
		//Controls
		handleInput();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		//System.out.println("Camera combined: " + camera.combined);
		//Clear screen
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//dummyMap.draw(batch);
		//batch.draw(dummyMapTexture, 0, 0);
		dummyMap.draw(batch);
		yogish.draw(batch);
		
		batch.end();
		
	
		
	}
	
	private void handleInput()
	{
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			
			x -= 10;
			if(x<=leftTrigger)
			{
				rightTrigger -=10;
				leftTrigger -=10;
				camera.translate(-10, 0, 0);
				
			}
			//if(x<=0) {
			yogish.setPosition(x, y);
			System.out.println(x);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			x +=10;
		//IF width is 900, the next instance of camera movement is from x = 10 to 910. Trigger point 600. Must update trigger point. 
			if( x >= rightTrigger)
			{
				rightTrigger +=10;
				leftTrigger +=10;
				camera.translate(10, 0, 0);
			}
			yogish.setPosition(x, y);
			System.out.println(x);
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
