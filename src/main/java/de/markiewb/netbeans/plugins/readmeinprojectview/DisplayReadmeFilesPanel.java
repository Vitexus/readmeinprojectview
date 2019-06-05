/*
 * Copyright 2019 Benno Markiewicz (benno.markiewicz@googlemail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.markiewb.netbeans.plugins.readmeinprojectview;

import java.util.regex.Pattern;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.util.NbPreferences;

@SuppressWarnings("serial")
final class DisplayReadmeFilesPanel extends javax.swing.JPanel {

    private final DisplayReadmeFilesOptionsPanelController controller;

    DisplayReadmeFilesPanel(DisplayReadmeFilesOptionsPanelController controller) {
        this.controller = controller;
        initComponents();

        textareaFilenames.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                DisplayReadmeFilesPanel.this.controller.changed();
            }

            @Override
            public void removeUpdate(DocumentEvent event) {
                DisplayReadmeFilesPanel.this.controller.changed();
            }

            @Override
            public void changedUpdate(DocumentEvent event) {
                DisplayReadmeFilesPanel.this.controller.changed();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textareaFilenames = new javax.swing.JTextArea();
        labelDescription = new javax.swing.JLabel();

        textareaFilenames.setColumns(20);
        textareaFilenames.setRows(5);
        jScrollPane1.setViewportView(textareaFilenames);

        org.openide.awt.Mnemonics.setLocalizedText(labelDescription, org.openide.util.NbBundle.getMessage(DisplayReadmeFilesPanel.class, "DisplayReadmeFilesPanel.labelDescription.text")); // NOI18N
        labelDescription.setToolTipText(org.openide.util.NbBundle.getMessage(DisplayReadmeFilesPanel.class, "DisplayReadmeFilesPanel.labelDescription.toolTipText")); // NOI18N
        labelDescription.setFocusable(false);
        labelDescription.setRequestFocusEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(labelDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );

        labelDescription.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(DisplayReadmeFilesPanel.class, "DisplayReadmeFilesPanel.labelDescription.AccessibleContext.accessibleName")); // NOI18N
        labelDescription.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(DisplayReadmeFilesPanel.class, "DisplayReadmeFilesPanel.labelDescription.AccessibleContext.accessibleDescription")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    void load() {
        final String filenames = NbPreferences.forModule(DisplayReadmeFilesPanel.class).get(ReadmeNodeFactory.KEY_FILENAMES, ReadmeNodeFactory.DEFAULT_FILENAMES);
        textareaFilenames.setText(filenames);
    }

    void store() {
        final StringBuilder filenames = new StringBuilder();
        for (final String filename : textareaFilenames.getText().split(Pattern.quote("\n"))) {
            final String filenameClean = filename.trim().replace("\r", "");
            if (!filenameClean.isEmpty()) {
                filenames.append(filenameClean).append('\n');
            }
        }
        if (filenames.length() == 0) {
            filenames.append(ReadmeNodeFactory.DEFAULT_FILENAMES);
        }
        NbPreferences.forModule(DisplayReadmeFilesPanel.class).put(ReadmeNodeFactory.KEY_FILENAMES, filenames.toString());
    }

    boolean valid() {
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JTextArea textareaFilenames;
    // End of variables declaration//GEN-END:variables
}
