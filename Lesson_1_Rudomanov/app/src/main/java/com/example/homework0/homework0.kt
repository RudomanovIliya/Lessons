package com.example.homework0

import kotlin.math.max
import kotlin.math.min


private const val BOUNDS_MIN = 1
private const val BOUNDS_MAX = 30

val N = 50

open class Creatures(
    protected val name: String = "Undefined",
    protected var hp: Int = 0,
    protected val attack: Int = 0,
    protected val defence: Int = 0,
    protected val minDmg: Int = 0,
    protected val maxDmg: Int = 0,
) {
    val maxHp: Int = hp

    init {

        if (this.hp < 0 || this.hp > N) throw Exception("HP value is out bounds")
        if (this.attack > BOUNDS_MAX || this.attack < BOUNDS_MIN)
            throw Exception("Attack value is out bounds")
        if (this.defence > BOUNDS_MAX || this.defence < BOUNDS_MIN)
            throw Exception("Defence value is out bounds")
    }

    protected fun changeHp(chp: Int) {
        this.hp += chp
        this.hp = min(this.hp, this.maxHp)
    }

    fun attack(enemy: Creatures) {
        var attackMult = max(this.attack - enemy.getDefenc(), 0) + 1
        var cube = 0
        while (cube < 5 && attackMult > 0) {
            cube = (1..6).random()
            attackMult -= 1
        }
        if (cube >= 5)
            enemy.changeHp(-(this.minDmg..this.maxDmg).random())

    }

    fun getHP(): Int {
        return this.hp
    }

    fun getDefenc(): Int {
        return this.defence
    }

}

class Monster(
    name: String,
    hp: Int,
    attack: Int,
    defence: Int,
    minDmg: Int,
    maxDmg: Int,
) : Creatures(name, hp, attack, defence, minDmg, maxDmg)

class Player(
    name: String,
    hp: Int,
    attack: Int,
    defence: Int,
    minDmg: Int,
    maxDmg: Int,
) : Creatures(name, hp, attack, defence, minDmg, maxDmg) {
    private var healLimit = 4
    fun heal() {
        if (this.healLimit > 0) {
            this.changeHp((this.maxHp * 0.3).toInt())
            this.healLimit -= 1
        }

    }
}


/*fun main() {
    val player1 = Player("Player1", 20, 10, 5, 1, 6)
    player1.heal()
    val goblin1 = Monster("Goblin", 30, 10, 10, 1, 6)

}*/