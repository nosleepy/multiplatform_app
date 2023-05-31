@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE", "EXPOSED_PARAMETER_TYPE")

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.native.ComposeLayer
import app.cash.sqldelight.db.SqlDriver
import com.grandstream.multiplatform.MainView
import org.jetbrains.skiko.wasm.onWasmReady
import com.grandstream.multiplatform.createDriverPromise
import com.grandstream.multiplatform.di.initKoin
import com.grandstream.multiplatform.entity.Contact
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.dom.create
import kotlinx.html.js.table
import kotlinx.html.tbody
import kotlinx.html.td
import kotlinx.html.th
import kotlinx.html.thead
import kotlinx.html.tr
import moe.tlaster.precompose.preComposeWindow
import org.w3c.dom.HTMLCanvasElement
import kotlin.js.Promise

var canvas = document.getElementById("ComposeTarget") as HTMLCanvasElement

fun canvasResize(width: Int = window.innerWidth, height: Int = window.innerHeight) {
    canvas.setAttribute("width", "$width")
    canvas.setAttribute("height", "$height")
}

fun composableResize(layer: ComposeLayer) {
    val clone = canvas.cloneNode(false) as HTMLCanvasElement
    canvas.replaceWith(clone)
    canvas = clone

    val scale = layer.layer.contentScale
    canvasResize()
    layer.layer.attachTo(clone)
    layer.layer.needRedraw()
    layer.setSize(
        (clone.width / scale).toInt(),
        (clone.height / scale).toInt()
    )
}

//一、Compose-Android写法
@OptIn(ExperimentalJsExport::class)
fun main() {
    initKoin()
    Napier.base(DebugAntilog())
    val promise: Promise<SqlDriver> = createDriverPromise()
    promise.then {
        onWasmReady {
            canvasResize()
            preComposeWindow("Contact") {
                window.addEventListener("resize", {
//                    composableResize(layer = layer)
                })
                MainView(it)
            }
        }
    }
//    val contactList = listOf<Contact>(
//        Contact(1, "a", "111", "a@11.com", "杭州"),
//        Contact(2, "b", "222", "bbb@11.com", "长沙"),
//        Contact(3, "c", "333", "ccc@11.com", "上滑"),
//    )
//    val playersTable = document.create.table {
//        thead {
//            tr {
//                th { +"name" }
//                th { +"number" }
//                th { +"email" }
//                th { +"address" }
//            }
//        }
//        tbody {
//            contactList.forEach { contact ->
//                tr {
//                    td { +contact.name }
//                    td { +contact.number }
//                    td { +contact.email }
//                    td { +contact.address }
//                }
//            }
//        }
//    }
//    document.getElementById("contacts")?.append(playersTable)
}

//二、Compose-Web写法
//fun main() {
//    var count: Int by mutableStateOf(0)
//    renderComposable(rootElementId = "root") {
//        Div({ style { padding(25.px) } }) {
//            Button(attrs = {
//                onClick { count -= 1 }
//            }) {
//                Text("-")
//            }
//            Span({ style { padding(15.px) } }) {
//                Text("$count")
//            }
//            Button(attrs = {
//                onClick { count += 1 }
//            }) {
//                Text("+")
//            }
//        }
//        Div( { style {
//            background(Color.green.toString())
//            height(40.px)
//            width(40.px)
//        } } ) {
//
//        }
//    }
//}