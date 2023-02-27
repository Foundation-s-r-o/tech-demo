import React, { PropsWithChildren } from 'react'

const FndtText = ({
    value,
    children,
    id,
    ...props
}: PropsWithChildren & {
    value?: string | Date
    id?: string
}) => {
    var textValue = children;
    if (value) {
        textValue = value instanceof Date
            ? value.toLocaleDateString()
            : value;
    }

    return (
        <div
            {...props}
            id={id}
            className="form-value">
            {textValue}
        </div>
    )
}

export default FndtText
