package am.dragoapps.lap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.media.MediaPlayer

class Game(playersArray : Array<Player>, winButtons : Array<TextView>) {
	init {
		for (i in playersArray.indices) {
			playersArray[i].balanceAdd(1000) // TODO custom values
			
			winButtons[i].setOnClickListener {
				if (playersArray[i].betAdd(0) != 0) {
					// find sum of all players with this player
					var sum = 0
					for (j in playersArray.indices) {
						sum += playersArray[j].betAdd(0)
						playersArray[j].betAdd(-playersArray[j].betAdd(0))
					}
					playersArray[i].balanceAdd(sum)
				}
			}
		}
	}
}

class Player(betButtons: Array<TextView>, iBalanceBox: TextView, iBetBox: TextView) {
	private val balanceBox = iBalanceBox
	private val betBox = iBetBox
	
	
	private var balance: Int = 0
	private var bet: Int = 0
	
	public fun balanceAdd(addValue: Int) : Int {
		balance += addValue
		balanceBox.text = balance.toString()
		return balance
	}
	
	public fun betAdd(addValue: Int) : Int {
		bet += addValue
		betBox.text = bet.toString()
		return bet
	}
	
	init {
		val betArr = arrayOf(5, 15, 50, 100)
		
		for (i in betArr.indices) {
			betButtons[i].setOnClickListener {
				if (balance >= betArr[i]) {
					balanceAdd(-betArr[i])
					betAdd(betArr[i])
				}
			}
		}
	}
}

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		var mMediaPlayer: MediaPlayer? = null
		findViewById<TextView>(R.id.infoTextPlayerKurlyk).setOnClickListener {
			if (mMediaPlayer == null) {
				mMediaPlayer = MediaPlayer.create(this, R.raw.zdorovie)
				mMediaPlayer!!.isLooping = false
				mMediaPlayer!!.start()
			} else mMediaPlayer!!.start()
		}
		
		// Players create
		var arr = arrayOf(
			findViewById<TextView>(R.id.Player1Plus5),
			findViewById<TextView>(R.id.Player1Plus15),
			findViewById<TextView>(R.id.Player1Plus50),
			findViewById<TextView>(R.id.Player1Plus100)
			)
		val player1 = Player(arr, findViewById<TextView>(R.id.balancePlayer1), findViewById<TextView>(R.id.betPlayer1))
		arr = arrayOf(
			findViewById<TextView>(R.id.Player2Plus5),
			findViewById<TextView>(R.id.Player2Plus15),
			findViewById<TextView>(R.id.Player2Plus50),
			findViewById<TextView>(R.id.Player2Plus100)
		)
		val player2 = Player(arr, findViewById<TextView>(R.id.balancePlayer2), findViewById<TextView>(R.id.betPlayer2))
		arr = arrayOf(
			findViewById<TextView>(R.id.Player3Plus5),
			findViewById<TextView>(R.id.Player3Plus15),
			findViewById<TextView>(R.id.Player3Plus50),
			findViewById<TextView>(R.id.Player3Plus100)
		)
		val player3 = Player(arr, findViewById<TextView>(R.id.balancePlayer3), findViewById<TextView>(R.id.betPlayer3))
		arr = arrayOf(
			findViewById<TextView>(R.id.Player4Plus5),
			findViewById<TextView>(R.id.Player4Plus15),
			findViewById<TextView>(R.id.Player4Plus50),
			findViewById<TextView>(R.id.Player4Plus100)
		)
		val player4 = Player(arr, findViewById<TextView>(R.id.balancePlayer4), findViewById<TextView>(R.id.betPlayer4))
		arr = arrayOf(
			findViewById<TextView>(R.id.Player5Plus5),
			findViewById<TextView>(R.id.Player5Plus15),
			findViewById<TextView>(R.id.Player5Plus50),
			findViewById<TextView>(R.id.Player5Plus100)
		)
		val player5 = Player(arr, findViewById<TextView>(R.id.balancePlayer5), findViewById<TextView>(R.id.betPlayer5))
		arr = arrayOf(
			findViewById<TextView>(R.id.Player6Plus5),
			findViewById<TextView>(R.id.Player6Plus15),
			findViewById<TextView>(R.id.Player6Plus50),
			findViewById<TextView>(R.id.Player6Plus100)
		)
		val player6 = Player(arr, findViewById<TextView>(R.id.balancePlayer6), findViewById<TextView>(R.id.betPlayer6))
		val playersArray = arrayOf(player1, player2, player3, player4, player5, player6)
		val winButtons = arrayOf (
			findViewById<TextView>(R.id.player1win),
			findViewById<TextView>(R.id.player2win),
			findViewById<TextView>(R.id.player3win),
			findViewById<TextView>(R.id.player4win),
			findViewById<TextView>(R.id.player5win),
			findViewById<TextView>(R.id.player6win)
		)
		
		val gd = Game(playersArray, winButtons)
	}
}