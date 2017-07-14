import kotlin.browser.document

/**
 * Created by plter on 7/14/17.
 */

val electron: dynamic = js("require('electron')")

fun main(args: Array<String>) {
    document.body?.innerHTML = "<h1>Hello World</h1>"
}