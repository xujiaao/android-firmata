package com.xujiaao.android.firmata.sample

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.text.Html
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.*

class TipsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var mTips: TextView
    private lateinit var mImage: ImageView

    init {
        verticalLayout {
            backgroundColor = Color.WHITE
            padding = dip(16)

            mTips = textView().lparams(matchParent, wrapContent)
            mImage = imageView {
                adjustViewBounds = true
                maxHeight = dip(320)
            }.lparams(matchParent, wrapContent) {
                margin = dip(16)
            }
        }

        @SuppressLint("Recycle")
        val a = context.obtainStyledAttributes(attrs, R.styleable.TipsView, defStyleAttr, 0)
        setTips(a.getTextArray(R.styleable.TipsView_tips))
        setImage(a.getDrawable(R.styleable.TipsView_image))
        a.recycle()
    }

    fun setTips(textsRes: Int) =
        setTips(if (textsRes > 0) resources.getTextArray(textsRes) else null)

    private fun setTips(texts: Array<CharSequence>?) {
        val html = if (texts != null && !texts.isEmpty()) {
            Html.fromHtml(
                StringBuilder().apply {
                    append("<ul>")
                    texts.forEach { append("<li>&nbsp;&nbsp;").append(it).append("</li>") }
                    append("</ul>")
                }.toString()
            )
        } else null

        mTips.visibility = if (html != null) View.VISIBLE else View.GONE
        mTips.text = html

        updateVisibility()
    }

    fun setImage(imageRes: Int) =
        setImage(if (imageRes > 0) ContextCompat.getDrawable(context, imageRes) else null)

    private fun setImage(image: Drawable?) {
        mImage.visibility = if (image != null) View.VISIBLE else View.GONE
        mImage.setImageDrawable(image)

        updateVisibility()
    }

    private fun updateVisibility() {
        val visible = mTips.visibility == View.VISIBLE || mImage.visibility == View.VISIBLE
        visibility = if (visible) View.VISIBLE else View.GONE
    }
}