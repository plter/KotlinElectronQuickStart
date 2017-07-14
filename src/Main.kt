/**
 * Created by plter on 7/14/17.
 */
external val process: dynamic
external val APP_PATH: String

class Main() {
    val electron: dynamic = js("require('electron')")
    val app: dynamic = electron.app

    val url: dynamic = js("require('url')")
    val path: dynamic = js("require('path')")

    var mainWindow: dynamic = null
    val windowWidth: Int = 800
    val windowHeight: Int = 600


    fun createWindow() {

        mainWindow = js("new this.electron.BrowserWindow({width:$windowWidth,height:$windowHeight})")

        mainWindow.loadURL(url.format(object {
            val pathname: String = path.join(APP_PATH, "out", "production", "First", "index.html")
            val protocol: String = "file:"
            val slashes: Boolean = true
        }))

        // Open the DevTools.
        // mainWindow.webContents.openDevTools()

        // Emitted when the window is closed.
        mainWindow.on("closed", fun() {
            mainWindow = null
        })
    }

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.


    fun addListeners() {
        app.on("ready", fun() {

        })

        app.on("window-all-closed", fun() {
            // On OS X it is common for applications and their menu bar
            // to stay active until the user quits explicitly with Cmd + Q
            if (process.platform !== "darwin") {
                app.quit()
            }
        })

        app.on("activate", fun() {
            // On OS X it's common to re-create a window in the app when the
            // dock icon is clicked and there are no other windows open.
            if (mainWindow === null) {
                createWindow()
            }
        })
    }

    init {
        addListeners()
    }
}


fun main(args: Array<String>) {
    Main()
}