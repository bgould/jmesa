/*
 * Copyright 2004 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jmesa.facade.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringUtils;
import org.jmesa.core.CoreContext;
import org.jmesa.limit.Order;
import org.jmesa.util.ItemUtils;
import org.jmesa.util.SupportUtils;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.editor.FilterEditor;
import org.jmesa.view.editor.HeaderEditor;
import org.jmesa.view.html.HtmlComponentFactory;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.renderer.HtmlCellRenderer;
import org.jmesa.view.html.renderer.HtmlFilterRenderer;
import org.jmesa.view.html.renderer.HtmlHeaderRenderer;
import org.jmesa.web.WebContext;

/**
 * Represents an HtmlColumn.
 * 
 * @since 2.1
 * @author jeff jie
 */
public class HtmlColumnTag extends SimpleTagSupport {
    private String property;
    private String title;
    private String titleKey;
    private boolean sortable = true;
    private String sortOrder;
    private boolean filterable = true;
    private String width;
    private String cellRenderer;
    private String filterRenderer;
    private String headerRenderer;
    private String style;
    private String styleClass;
    private String filterStyle;
    private String filterClass;
    private String headerStyle;
    private String headerClass;
    private String pattern;
    private String cellEditor;
    private String headerEditor;
    private String filterEditor;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    /**
     * @since 2.2
     * @return The sort order for the column.
     */
    public String getSortOrder() {
        return sortOrder;
    }

    /**
     * @since 2.2
     * @param sortOrder The order array.
     */
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isFilterable() {
        return filterable;
    }

    public void setFilterable(boolean filterable) {
        this.filterable = filterable;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    /**
     * @since 2.2
     */
    public String getCellRenderer() {
        return cellRenderer;
    }

    /**
     * @since 2.2
     */
    public void setCellRenderer(String cellRenderer) {
        this.cellRenderer = cellRenderer;
    }

    /**
     * @since 2.2
     */
    public String getFilterRenderer() {
        return filterRenderer;
    }

    /**
     * @since 2.2
     */
    public void setFilterRenderer(String filterRenderer) {
        this.filterRenderer = filterRenderer;
    }

    /**
     * @since 2.2
     */
    public String getHeaderRenderer() {
        return headerRenderer;
    }

    /**
     * @since 2.2
     */
    public void setHeaderRenderer(String headerRenderer) {
        this.headerRenderer = headerRenderer;
    }

    /**
     * @since 2.2.1
     */
    public String getStyle() {
        return style;
    }

    /**
     * @since 2.2.1
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * @since 2.2.1
     */
    public String getStyleClass() {
        return styleClass;
    }

    /**
     * @since 2.2.1
     */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @since 2.2
     */
    public String getFilterStyle() {
        return filterStyle;
    }

    /**
     * @since 2.2
     */
    public void setFilterStyle(String filterStyle) {
        this.filterStyle = filterStyle;
    }

    /**
     * @since 2.2
     */
    public String getFilterClass() {
        return filterClass;
    }

    /**
     * @since 2.2
     */
    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    /**
     * @since 2.2
     */
    public String getHeaderStyle() {
        return headerStyle;
    }

    /**
     * @since 2.2
     */
    public void setHeaderStyle(String headerStyle) {
        this.headerStyle = headerStyle;
    }

    /**
     * @since 2.2
     */
    public String getHeaderClass() {
        return headerClass;
    }

    /**
     * @since 2.2
     */
    public void setHeaderClass(String headerClass) {
        this.headerClass = headerClass;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getCellEditor() {
        return cellEditor;
    }

    public void setCellEditor(String cellEditor) {
        this.cellEditor = cellEditor;
    }

    /**
     * @since 2.2
     * @return The header editor for the column.
     */
    public String getHeaderEditor() {
        return headerEditor;
    }

    /**
     * @since 2.2
     * @param headerEditor The header editor to use.
     */
    public void setHeaderEditor(String headerEditor) {
        this.headerEditor = headerEditor;
    }

    /**
     * @since 2.2
     * @return The filter editor for the column.
     */
    public String getFilterEditor() {
        return filterEditor;
    }

    /**
     * @since 2.2
     * @param filterEditor The filter editor to use.
     */
    public void setFilterEditor(String filterEditor) {
        this.filterEditor = filterEditor;
    }

    /**
     * @since 2.2
     * @return The column CellRenderer object.
     */
    private HtmlCellRenderer getColumnCellRenderer(HtmlColumn column) {
        if (StringUtils.isBlank(getCellRenderer())) {
            return column.getCellRenderer();
        }
        
        HtmlCellRenderer cellRenderer = (HtmlCellRenderer) ClassUtils.createInstance(getCellRenderer());
        cellRenderer.setCellEditor(column.getCellRenderer().getCellEditor()); // reset the default

        return cellRenderer;
    }
    
    /**
     * <p>
     * If the cellEditor is not defined then create a BasicCellEditor.
     * </p>
     * 
     * <p>
     * If it is defined and it extends ContextSupport then set the WebContext and CoreContext on the
     * editor. If a setPattern() method is defined on your editor and you have defined the column
     * pattern attribute on the tag it will be set on your CellEditor automatically.
     * </p>
     * 
     * @return The CellEditor to use.
     */
    private CellEditor getColumnCellEditor(HtmlColumn column) {
        if (StringUtils.isEmpty(getCellEditor())) {
            return column.getCellRenderer().getCellEditor();
        }

        CellEditor editor = (CellEditor) ClassUtils.createInstance(getCellEditor());
        SupportUtils.setPattern(editor, getPattern());

        return editor;
    }
    
    /**
     * @since 2.2
     * @return The column FilterRenderer object.
     */
    private HtmlFilterRenderer getColumnFilterRenderer(HtmlColumn column) {
        if (StringUtils.isBlank(getFilterRenderer())) {
            return column.getFilterRenderer();
        }
        
        HtmlFilterRenderer filterRenderer = (HtmlFilterRenderer) ClassUtils.createInstance(getFilterRenderer());
        filterRenderer.setFilterEditor(column.getFilterRenderer().getFilterEditor()); // reset the default

        return filterRenderer;
    }
    
    /**
     * <p>
     * If it is defined and it extends ContextSupport then set the WebContext and CoreContext on the
     * editor.
     * </p>
     * 
     * @return The FilterEditor to use.
     */
    private FilterEditor getColumnFilterEditor(HtmlColumn column) {
        if (StringUtils.isEmpty(getFilterEditor())) {
            return column.getFilterRenderer().getFilterEditor();
        }

        return (FilterEditor) ClassUtils.createInstance(getFilterEditor());
    }

    /**
     * @since 2.2
     * @return The column HeaderRenderer object.
     */
    private HtmlHeaderRenderer getColumnHeaderRenderer(HtmlColumn column) {
        if (StringUtils.isBlank(getHeaderRenderer())) {
            return column.getHeaderRenderer();
        }
        
        HtmlHeaderRenderer headerRenderer = (HtmlHeaderRenderer) ClassUtils.createInstance(getHeaderRenderer());
        headerRenderer.setHeaderEditor(column.getHeaderRenderer().getHeaderEditor()); // reset the default

        return headerRenderer;
    }
    
    /**
     * <p>
     * If it is defined and it extends ContextSupport then set the WebContext and CoreContext on the
     * editor.
     * </p>
     * 
     * @return The HeaderEditor to use.
     */
    private HeaderEditor getColumnHeaderEditor(HtmlColumn column) {
        if (StringUtils.isEmpty(getHeaderEditor())) {
            return column.getHeaderRenderer().getHeaderEditor();
        }

        return (HeaderEditor) ClassUtils.createInstance(getHeaderEditor());
    }

    /**
     * Take the comma separted Order values and convert them into an Array. The legal values include
     * "none, asc, desc".
     * 
     * @since 2.2
     * @return The sort order array for the column.
     */
    private Order[] getColumnSortOrder() {
        if (StringUtils.isBlank(sortOrder)) {
            return null;
        }

        String[] orders = StringUtils.split(sortOrder, ",");

        Order[] results = new Order[orders.length];

        for (int i = 0; i < orders.length; i++) {
            String order = orders[i];
            Order valueOfParam = Order.valueOfParam(order);
            if (valueOfParam == null) {
                throw new IllegalStateException("The sortOrder must consist of comma separated values of asc, desc, and none.");
            }
            results[i] = valueOfParam;
        }

        return results;
    }

    /**
     * The column to use. If the column does not exist then one will be created.
     */
    private HtmlColumn getColumn(HtmlComponentFactory factory, WebContext webContext, CoreContext coreContext) {
        HtmlColumn column = factory.createColumn(getProperty());
        column.setTitle(getTitle());
        column.setTitleKey(getTitleKey());
        column.setSortable(isSortable());
        column.setSortOrder(getColumnSortOrder());
        column.setFilterable(isFilterable());
        column.setWidth(getWidth());

        // cell
        
        HtmlCellRenderer cellRenderer = getColumnCellRenderer(column);
        cellRenderer.setStyle(getStyle());
        cellRenderer.setStyleClass(getStyleClass());
        column.setCellRenderer(cellRenderer);
        
        CellEditor cellEditor = getColumnCellEditor(column);
        cellRenderer.setCellEditor(cellEditor);

        // filter

        HtmlFilterRenderer filterRenderer = getColumnFilterRenderer(column);
        filterRenderer.setStyle(getFilterStyle());
        filterRenderer.setStyleClass(getFilterClass());
        column.setFilterRenderer(filterRenderer);

        FilterEditor filterEditor = getColumnFilterEditor(column);
        filterRenderer.setFilterEditor(filterEditor);

        // header

        HtmlHeaderRenderer headerRenderer = getColumnHeaderRenderer(column);
        headerRenderer.setStyle(getHeaderStyle());
        headerRenderer.setStyleClass(getHeaderClass());
        column.setHeaderRenderer(headerRenderer);

        HeaderEditor headerEditor = getColumnHeaderEditor(column);
        headerRenderer.setHeaderEditor(headerEditor);

        return column;
    }

    /**
     * If a ColumnTag body is defined then use it as the current page item value. If it is not
     * defined then get the page item value from the regular bean by using the property
     * 
     * @return The item to use.
     */
    private Object getValue() throws JspException, IOException {
        TableFacadeTag facadeTag = (TableFacadeTag) findAncestorWithClass(this, TableFacadeTag.class);
        String var = facadeTag.getVar();
        Object item = getJspContext().getAttribute(var);

        if (item == null) {
            return null;
        }

        JspFragment body = getJspBody();
        if (body == null) {
            return ItemUtils.getItemValue(item, getProperty());
        }

        StringWriter value = new StringWriter();
        body.invoke(value);
        return value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        TableFacadeTag facadeTag = (TableFacadeTag) findAncestorWithClass(this, TableFacadeTag.class);
        Collection<Map<String, Object>> pageItems = facadeTag.getPageItems();
        if (pageItems.size() == 1) {
            HtmlRow row = facadeTag.getTable().getRow();
            
            HtmlComponentFactory factory = facadeTag.getComponentFactory();
            WebContext webContext = facadeTag.getWebContext();
            CoreContext coreContext = facadeTag.getCoreContext();
            
            HtmlColumn column = getColumn(factory, webContext, coreContext);
            TagUtils.validateColumn(this, getProperty());
            row.addColumn(column);
        }

        HtmlRowTag rowTag = (HtmlRowTag) findAncestorWithClass(this, HtmlRowTag.class);
        Map<String, Object> pageItem = rowTag.getPageItem();
        pageItem.put(getProperty(), getValue());
    }
}