package ru.rctikk.ml.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Button {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String tag;
    private String className;
    private String type;
    private Boolean onClick;
    private Boolean border;

    private Boolean isButton;
    private String hash;

    public Button(String tag, String className, String type, Boolean onClick, Boolean border, Boolean isButton) {
        this.tag = tag;
        this.className = className;
        this.type = type;
        this.onClick = onClick;
        this.border = border;
        this.isButton = isButton;
        computeHash();
    }

    public Button() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getOnClick() {
        return onClick;
    }

    public void setOnClick(Boolean onClick) {
        this.onClick = onClick;
    }

    public Boolean getBorder() {
        return border;
    }

    public void setBorder(Boolean border) {
        this.border = border;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Button button = (Button) o;
        return Objects.equals(tag, button.tag) && Objects.equals(className, button.className) && Objects.equals(type, button.type) && Objects.equals(onClick, button.onClick) && Objects.equals(border, button.border);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, className, type, onClick, border);
    }

    @Override
    public String toString() {
        return "Button{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", className='" + className + '\'' +
                ", type='" + type + '\'' +
                ", onClick=" + onClick +
                ", border=" + border +
                '}';
    }

    public String getHash() {
        return String.valueOf(hashCode());
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void computeHash() {
        this.hash = String.valueOf(hashCode());
    }

    public Boolean getButton() {
        return isButton;
    }

    public void setButton(Boolean button) {
        isButton = button;
    }
}
